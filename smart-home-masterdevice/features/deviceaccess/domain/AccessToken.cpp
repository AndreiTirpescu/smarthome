#include "AccessToken.h"

deviceaccess::AccessToken::AccessToken(std::string accessToken, std::string refreshToken)
    : accessToken(std::move(accessToken))
    , refreshToken(std::move(refreshToken))
{
}
