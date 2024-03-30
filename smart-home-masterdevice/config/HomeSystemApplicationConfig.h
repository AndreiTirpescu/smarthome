#ifndef MASTERDEVICE_HOMESYSTEMAPPLICATIONCONFIG_H
#define MASTERDEVICE_HOMESYSTEMAPPLICATIONCONFIG_H

#include "ChangelogRunner.h"
#include <QFont>
#include <QGuiApplication>
#include <QProcessEnvironment>

namespace config {
class DbConnection;
}

namespace config {

using DbConnectionPtr = std::shared_ptr<config::DbConnection>;

class HomeSystemApplicationConfig {
public:
    static void runChangelogsOnStartup(DbConnectionPtr dbConnection);

    static void loadVirtualKeyboard(const std::string& configEnv);

    static void applyFont();

    static std::string getEnv(const std::string& envVar);

    static void enableVirtualKeyboard() { qputenv("QT_IM_MODULE", QByteArray("qtvirtualkeyboard")); }

private:
    static constexpr const char* path = "resources/changelogs";
};
}

#endif // MASTERDEVICE_HOMESYSTEMAPPLICATIONCONFIG_H
