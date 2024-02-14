#ifndef MASTERDEVICE_CHANGELOGRUNNER_H
#define MASTERDEVICE_CHANGELOGRUNNER_H

#include <fstream>
#include <memory>

namespace config {
class DbConnection;
}

namespace config {

using DbConnectionPtr = std::shared_ptr<config::DbConnection>;

class ChangelogRunner {

public:
    ChangelogRunner(DbConnectionPtr connectionPtr, const char* changelogPath);

    void runChangelogs();

    ~ChangelogRunner();

private:
    DbConnectionPtr dbConnection;
    std::string configFilePath;
};
}

#endif // MASTERDEVICE_CHANGELOGRUNNER_H
