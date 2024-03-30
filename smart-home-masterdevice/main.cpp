#include "ChangelogRunner.h"
#include "DbConnection.h"
#include "DeviceSetupComponent.h"
#include "HomeSystemApplicationConfig.h"
#include "features/deviceaccess/application/UserLoginComponent.h"
#include "features/navigation/NavigationComponent.h"
#include "framework/deviceaccess/SqliteAccessTokenProvider.h"
#include "framework/devicesetup/SqliteDeviceSetupProvider.h"

#include <QGuiApplication>
#include <QNetworkAccessManager>
#include <QNetworkRequest>
#include <QProcessEnvironment>
#include <QQmlApplicationEngine>

using Configurator = config::HomeSystemApplicationConfig;
using TokenProviderPtr = std::shared_ptr<deviceaccess::IAccessTokenProvider>;
using DeviceSetupProviderPtr = std::shared_ptr<devicesetup::IDeviceSetupProvider>;

static const std::string DB_PATH = "MHS_DB_PATH";
static const std::string VKEYBOARD_ENABLED = "MHS_VKEYBOARD_ENABLED";

int main(int argc, char* argv[])
{
    Configurator::loadVirtualKeyboard(VKEYBOARD_ENABLED);

    QGuiApplication application(argc, argv);
    QQmlApplicationEngine engine;

    const auto db = std::make_shared<config::DbConnection>(Configurator::getEnv(DB_PATH));

    Configurator::applyFont();
    Configurator::runChangelogsOnStartup(db);

    const TokenProviderPtr tokenProvider = std::make_shared<framework::SqliteAccessTokenProvider>(db);
    const DeviceSetupProviderPtr deviceSetupProvider = std::make_shared<framework::SqliteDeviceSetupProvider>(db);
    const auto networkClient = new QNetworkAccessManager(&application);

    QObject::connect(&application, &QGuiApplication::destroyed, networkClient, &QNetworkAccessManager::deleteLater);

    qmlRegisterSingletonType<deviceaccess::UserLoginComponent>(
        "device.access", 1, 0, "UserLogin", [&](QQmlEngine*, QJSEngine*) -> QObject* {
            return new deviceaccess::UserLoginComponent(&application, tokenProvider, networkClient);
        });

    qmlRegisterSingletonType<devicesetup::DeviceSetupComponent>(
        "device.setup", 1, 0, "DeviceSetup", [&](QQmlEngine*, QJSEngine*) -> QObject* {
            return new devicesetup::DeviceSetupComponent(&application, deviceSetupProvider);
        });

    qmlRegisterSingletonType<devicesetup::DeviceSetupComponent>("navigation", 1, 0, "Navigator",
        [&](QQmlEngine*, QJSEngine*) -> QObject* { return new navigation::NavigationComponent(&application); });

    engine.addImportPath("qrc:/");
    engine.load(QUrl(QStringLiteral("qrc:view/main.qml")));

    return QCoreApplication::exec();
}
