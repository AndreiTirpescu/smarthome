#ifndef MDFRAMEWORK_SESSIONNETWORKING_H
#define MDFRAMEWORK_SESSIONNETWORKING_H

#include "LoginResponse.h"

namespace mdframework::session::infrastructure {
class SessionNetworking {
public:
    SessionNetworking(std::string baseUrl, int port);
    LoginResponse authV1Login(const std::string& email, const std::string& password);

private:
    static constexpr const char* const API_V1_LOGIN = "/api/v1/auth/login";
    static constexpr const char* const CONTENT_TYPE = "application/json";

    std::string baseUrl;
    int port;
};
}

#endif // MDFRAMEWORK_SESSIONNETWORKING_H
