#include "SqliteAccessTokenProvider.h"

#include <QDebug>
#include <utility>

#include "AccessToken.h"

framework::SqliteAccessTokenProvider::SqliteAccessTokenProvider(DbConnectionPtr dbConnection)
    : dbConnection(std::move(dbConnection))
{
}

AccessTokenPtr framework::SqliteAccessTokenProvider::storedAccessToken()
{
    qDebug() << "WAAA";
    return std::make_shared<AccessToken>("", "");
}
