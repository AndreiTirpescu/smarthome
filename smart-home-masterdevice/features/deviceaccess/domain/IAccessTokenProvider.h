#ifndef MASTERDEVICE_IACCESSTOKENPROVIDER_H
#define MASTERDEVICE_IACCESSTOKENPROVIDER_H

#include <memory>

namespace deviceaccess {

class AccessToken;

class IAccessTokenProvider {
public:
    virtual std::shared_ptr<AccessToken> storedAccessToken() = 0;
};

} // namespace deviceaccess

#endif
