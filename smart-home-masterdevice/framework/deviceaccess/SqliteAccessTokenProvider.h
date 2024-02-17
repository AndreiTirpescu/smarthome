#ifndef MASTERDEVICE_SQLITEACCESSTOKENPROVIDER_H
#define MASTERDEVICE_SQLITEACCESSTOKENPROVIDER_H

#include "IAccessTokenProvider.h"

namespace config {
class DbConnection;
}

using AccessToken = deviceaccess::AccessToken;
using AccessTokenPtr = std::shared_ptr<deviceaccess::AccessToken>;
using DbConnectionPtr = std::shared_ptr<config::DbConnection>;

namespace framework {
class SqliteAccessTokenProvider : public deviceaccess::IAccessTokenProvider {
public:
    explicit SqliteAccessTokenProvider(DbConnectionPtr dbConnection);

    AccessTokenPtr storedAccessToken() override;
    AccessTokenPtr saveToken(const std::string& accessToken, const std::string& refreshToken) override;

private:
    DbConnectionPtr dbConnection;
};
}

#endif
