#include "ChangelogRunner.h"
#include "DbConnection.h"
#include <QDebug>
#include <QGuiApplication>
#include <QQmlApplicationEngine>

static constexpr const char* DB_PATH = "/home/andrei/work/smarthome/masterdevice.db";
static constexpr const char* path = "resources/changelogs";

int main(int argc, char* argv[])
{
    QGuiApplication application(argc, argv);
    QQmlApplicationEngine engine;

    const auto db = std::make_shared<config::DbConnection>(DB_PATH);

    {
        auto changelogRunner = config::ChangelogRunner(db, path);
        changelogRunner.runChangelogs();
    }

    engine.load(QUrl(QStringLiteral("qrc:view/main.qml")));

    return QCoreApplication::exec();
}
