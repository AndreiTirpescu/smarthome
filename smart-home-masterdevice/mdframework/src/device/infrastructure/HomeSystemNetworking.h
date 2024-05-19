#ifndef MASTERDEVICE_HOMESYSTEMNETWORKING_H
#define MASTERDEVICE_HOMESYSTEMNETWORKING_H

#include "HomeSystemResponse.h"
#include <string>

namespace mdframework::devices::infrastructure {
class HomeSystemNetworking {
public:
    HomeSystemNetworking(std::string baseUrl, int port);
    HomeSystemResponse homeSystemsV1Connect(
        const std::string& name, const std::string& identityId, const std::string& accessToken);

private:
    static constexpr const char* const API_V1_HOME_SYSTEMS_CONNECT = "/api/v1/homesystems";
    static constexpr const char* const CONTENT_TYPE = "application/json";
    static constexpr const char* const BEARED_TOKEN = "Bearer {}";

    std::string baseUrl;
    int port;
};
}

#endif // MASTERDEVICE_HOMESYSTEMNETWORKING_H
