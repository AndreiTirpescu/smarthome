#ifndef MASTERDEVICE_IACCESSTOKENPROVIDER_H
#define MASTERDEVICE_IACCESSTOKENPROVIDER_H

#include "AccessToken.h"
#include <memory>

namespace deviceaccess {

class IAccessTokenProvider {
public:
    virtual const std::unique_ptr<AccessToken> storedAccessToken() = 0;
};

} // namespace deviceaccess

#endif
