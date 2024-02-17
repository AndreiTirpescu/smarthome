#include "ChangelogRunner.h"
#include "DbConnection.h"
#include "HomeSystemApplicationConfig.h"
#include "features/deviceaccess/application/UserLoginComponent.h"
#include "framework/deviceaccess/SqliteAccessTokenProvider.h"

#include <QGuiApplication>
#include <QNetworkAccessManager>
#include <QNetworkRequest>
#include <QProcessEnvironment>
#include <QQmlApplicationEngine>

using Configurator = config::HomeSystemApplicationConfig;
using TokenProviderPtr = std::shared_ptr<deviceaccess::IAccessTokenProvider>;

static const std::string DB_PATH = "MHS_DB_PATH";

int main(int argc, char* argv[])
{
    QGuiApplication application(argc, argv);
    QQmlApplicationEngine engine;

    const auto db = std::make_shared<config::DbConnection>(Configurator::getEnv(DB_PATH));

    Configurator::applyFont();
    Configurator::runChangelogsOnStartup(db);

    const TokenProviderPtr tokenProvider = std::make_shared<framework::SqliteAccessTokenProvider>(db);
    const auto networkClient = new QNetworkAccessManager(&application);

    QObject::connect(&application, &QGuiApplication::destroyed, networkClient, &QNetworkAccessManager::deleteLater);

    qmlRegisterSingletonType<deviceaccess::UserLoginComponent>(
        "device.access", 1, 0, "UserLogin", [&](QQmlEngine*, QJSEngine*) -> QObject* {
            return new deviceaccess::UserLoginComponent(&application, tokenProvider, networkClient);
        });

    engine.addImportPath("qrc:/");
    engine.load(QUrl(QStringLiteral("qrc:view/main.qml")));

    return QCoreApplication::exec();
}
