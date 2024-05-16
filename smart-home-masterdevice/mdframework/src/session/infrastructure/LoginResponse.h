#ifndef TEST_MDFRAMEWORK_LOGINRESPONSE_H
#define TEST_MDFRAMEWORK_LOGINRESPONSE_H

#include <string>

struct LoginResponse {
    std::string accessToken;
    std::string refreshToken;
};

#endif // TEST_MDFRAMEWORK_LOGINRESPONSE_H
