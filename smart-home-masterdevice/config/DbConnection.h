#ifndef MASTERDEVICE_DBCONNECTION_H
#define MASTERDEVICE_DBCONNECTION_H

#include <QtSql/QSqlDatabase>
#include <QtSql/QSqlError>
#include <QtSql/QSqlQuery>
#include <memory>

namespace config {

class DbConnection {
public:
    DbConnection(const char* dbPath);

    QSqlQuery query(const std::string& queryString);

    bool isOpen();

    ~DbConnection();

private:
    QSqlDatabase dbConn;
};
}

#endif // MASTERDEVICE_DBCONNECTION_H
