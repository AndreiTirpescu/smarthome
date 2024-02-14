#include "SqliteAccessTokenProvider.h"

const std::unique_ptr<AccessToken> deviceaccess::SqliteAccessTokenProvider::storedAccessToken()
{
    return std::make_unique<AccessToken>("", "");
}
