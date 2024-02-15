#include "ChangelogRunner.h"
#include "DbConnection.h"
#include "HomeSystemApplicationConfig.h"

#include <QGuiApplication>
#include <QProcessEnvironment>
#include <QQmlApplicationEngine>

using Configurator = config::HomeSystemApplicationConfig;

static const std::string DB_PATH = "MHS_DB_PATH";

int main(int argc, char* argv[])
{
    QGuiApplication application(argc, argv);
    QQmlApplicationEngine engine;

    const auto db = std::make_shared<config::DbConnection>(Configurator::getEnv(DB_PATH));

    Configurator::applyFont();
    Configurator::runChangelogsOnStartup(db);

    engine.load(QUrl(QStringLiteral("qrc:view/main.qml")));

    return QCoreApplication::exec();
}
