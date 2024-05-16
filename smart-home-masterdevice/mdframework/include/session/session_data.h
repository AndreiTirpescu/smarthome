#ifndef MDFRAMEWORK_SESSION_H
#define MDFRAMEWORK_SESSION_H

#include <string>

namespace mdframework::session {
struct session_data {
    std::string accessToken;
    std::string refreshToken;
    long tokenExpiresAt;
    std::string userId;
    std::string email;
};
}

#endif // MDFRAMEWORK_SESSION_H
