#include "DbConnection.h"

config::DbConnection::DbConnection(const std::string& dbPath)
{
    qDebug() << "Opening db connection to" << dbPath;

    dbConn = QSqlDatabase::addDatabase("QSQLITE");
    dbConn.setDatabaseName(dbPath.c_str());

    dbConn.open();
}

QSqlQuery config::DbConnection::query(const std::string& queryString)
{
    QSqlQuery result { dbConn };
    result.prepare(QString(queryString.c_str()));

    return result;
}

config::DbConnection::~DbConnection()
{
    qDebug() << "Database connection closed";
    dbConn.close();
}

bool config::DbConnection::isOpen() { return dbConn.isOpen(); }
