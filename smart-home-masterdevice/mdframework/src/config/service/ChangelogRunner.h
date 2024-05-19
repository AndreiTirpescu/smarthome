#ifndef MDFRAMEWORKWITHTEST_CHANGELOGRUNNER_H
#define MDFRAMEWORKWITHTEST_CHANGELOGRUNNER_H

#include <SQLiteCpp/Database.h>
class ChangelogRunner {
public:
    void runChangelogs();
    void createSessionTable(const SQLite::Database& db) const;
    void createMasterDeviceTable(const SQLite::Database& db) const;
};

#endif // MDFRAMEWORKWITHTEST_CHANGELOGRUNNER_H
