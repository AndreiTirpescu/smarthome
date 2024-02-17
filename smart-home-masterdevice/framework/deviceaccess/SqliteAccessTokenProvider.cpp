#include "SqliteAccessTokenProvider.h"

#include <QDebug>
#include <QSqlQuery>
#include <utility>

#include "AccessToken.h"
#include "DbConnection.h"

framework::SqliteAccessTokenProvider::SqliteAccessTokenProvider(DbConnectionPtr dbConnection)
    : dbConnection(std::move(dbConnection))
{
}

AccessTokenPtr framework::SqliteAccessTokenProvider::storedAccessToken()
{
    return std::make_shared<AccessToken>("", "");
}

AccessTokenPtr framework::SqliteAccessTokenProvider::saveToken(
    const std::string& accessToken, const std::string& refreshToken)
{
    QSqlQuery fetchQuery = dbConnection->query("SELECT access_token, refresh_token FROM tokens LIMIT 1");

    fetchQuery.exec();

    if (fetchQuery.first()) {
        QSqlQuery update = dbConnection->query(
            "UPDATE tokens SET access_token = :accessToken, refresh_token = :refreshToken WHERE id = 1");
        
        update.bindValue(":accessToken", QString(accessToken.c_str()));
        update.bindValue(":refreshToken", QString(refreshToken.c_str()));

        if (!update.exec()) {
            qCritical() << "Could not store data" << update.lastError().text();
        }

        return std::make_shared<AccessToken>(accessToken, refreshToken);
    }

    QSqlQuery insert = std::move(
        dbConnection->query("INSERT INTO tokens(access_token, refresh_token) VALUES(:accessToken, :refreshToken)"));

    insert.bindValue(":accessToken", QString(accessToken.c_str()));
    insert.bindValue(":refreshToken", QString(accessToken.c_str()));

    if (!insert.exec()) {
        qCritical() << "Could not store data" << insert.lastError().text();
    }

    return std::make_shared<AccessToken>(accessToken, refreshToken);
}
