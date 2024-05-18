#include "NavigationComponent.h"
#include "config/config_api.h"
#include "features/deviceaccess/UserLoginComponent.h"

#include <QGuiApplication>
#include <QNetworkRequest>
#include <QQmlApplicationEngine>

int main(int argc, char* argv[])
{
    qputenv("QT_IM_MODULE", QByteArray("qtvirtualkeyboard"));

    QGuiApplication application(argc, argv);
    QQmlApplicationEngine engine;

    mdframework::config::initialize_library(argv[1]);

    qmlRegisterSingletonType<deviceaccess::UserLoginComponent>("device.access", 1, 0, "UserLogin",
        [&](QQmlEngine*, QJSEngine*) -> QObject* { return new deviceaccess::UserLoginComponent(&application); });
    qmlRegisterSingletonType<navigation::NavigationComponent>("navigation", 1, 0, "Navigator",
        [&](QQmlEngine*, QJSEngine*) -> QObject* { return new navigation::NavigationComponent(&application); });

    QObject::connect(&application, &QGuiApplication::aboutToQuit, []() { mdframework::config::clear_library(); });

    engine.addImportPath("qrc:/");
    engine.load(QUrl(QStringLiteral("qrc:view/main.qml")));

    return QCoreApplication::exec();
}
