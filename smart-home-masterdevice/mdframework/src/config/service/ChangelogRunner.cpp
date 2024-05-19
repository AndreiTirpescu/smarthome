#include "ChangelogRunner.h"
#include "SQLiteCpp/Database.h"
#include "config/locator/ServiceLocator.h"
#include "config/model/AppConfig.h"

void ChangelogRunner::runChangelogs()
{
    const auto dbPath = ServiceLocator::instance().resolve<AppConfig>()->dbPath();

    SQLite::Database db(dbPath, SQLite::OPEN_READWRITE | SQLite::OPEN_CREATE);

    createSessionTable(db);
    createMasterDeviceTable(db);
}

void ChangelogRunner::createSessionTable(const SQLite::Database& db) const
{
    const auto createSessionTable
        = "CREATE TABLE IF NOT EXISTS session (id INTEGER PRIMARY KEY AUTOINCREMENT, "
          "access_token TEXT, refresh_token TEXT, token_expires_at BIGINT, user_id VARCHAR(36), email VARCHAR(255))";
    SQLite::Statement statement(db, createSessionTable);

    statement.exec();
}

void ChangelogRunner::createMasterDeviceTable(const SQLite::Database& db) const
{
    const auto createMasterDeviceTable = "CREATE TABLE IF NOT EXISTS master_device "
                                         "(id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(32), "
                                         "home_system_id VARCHAR(36), identity_id VARCHAR(36))";
    SQLite::Statement statement(db, createMasterDeviceTable);
    statement.exec();
}
