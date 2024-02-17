#ifndef MASTERDEVICE_NETWORKERRORRESPONSE_H
#define MASTERDEVICE_NETWORKERRORRESPONSE_H

#include <map>
#include <string>
#include <vector>

namespace config::dtos {
using ErrorDetails = std::map<std::string, std::string>;

struct ErrorResponse {
    std::string key;
    std::string debugMessage;
    int status;
    ErrorDetails details;
};
}

#endif // MASTERDEVICE_NETWORKERRORRESPONSE_H
