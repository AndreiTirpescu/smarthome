#include "ChangelogRunner.h"
#include "DbConnection.h"

#include <QtSql/QSqlDatabase>
#include <QtSql/QSqlError>
#include <QtSql/QSqlQuery>
#include <fstream>
#include <string>
#include <utility>

config::ChangelogRunner::ChangelogRunner(DbConnectionPtr connectionPtr, const char* changelogPath)
    : dbConnection(std::move(connectionPtr))
    , configFilePath(changelogPath)
{
    if (!dbConnection->isOpen()) {
        qCritical() << "Could not run changelogs at startup, database not opened";

        throw std::runtime_error("Invalid database configuration");
    }
}

void config::ChangelogRunner::runChangelogs()
{
    std::string currentQuery {};
    int currentChangelog {};

    std::ifstream configFile { configFilePath };

    while (std::getline(configFile, currentQuery)) {
        qDebug() << "executing changelog #" << currentChangelog;

        QSqlQuery query = dbConnection->query(currentQuery);

        if (!query.exec()) {
            qCritical() << query.lastError().text();
            throw std::runtime_error("Invalid database configuration");
        }
    }
}

config::ChangelogRunner::~ChangelogRunner() { qDebug() << "Finished running changelogs"; }
