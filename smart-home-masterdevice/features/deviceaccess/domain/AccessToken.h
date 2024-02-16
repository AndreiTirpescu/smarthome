#ifndef MASTERDEVICE_ACCESSTOKEN_H
#define MASTERDEVICE_ACCESSTOKEN_H

#include <string>
#include <utility>

namespace deviceaccess {

class AccessToken {
public:
    AccessToken(std::string accessToken, std::string refreshToken);

private:
    std::string accessToken;
    std::string refreshToken;
};

} // namespace deviceaccess

#endif