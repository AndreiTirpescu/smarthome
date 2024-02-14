#ifndef MASTERDEVICE_SQLITEACCESSTOKENPROVIDER_H
#define MASTERDEVICE_SQLITEACCESSTOKENPROVIDER_H

#include "IAccessTokenProvider.h"

using AccessToken = deviceaccess::AccessToken;

namespace deviceaccess {
class SqliteAccessTokenProvider : public IAccessTokenProvider {
public:
    const std::unique_ptr<AccessToken> storedAccessToken() override;
};
}

#endif
