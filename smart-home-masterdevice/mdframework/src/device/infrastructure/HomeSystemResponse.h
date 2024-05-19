#ifndef MASTERDEVICE_HOMESYSTEMRESPONSE_H
#define MASTERDEVICE_HOMESYSTEMRESPONSE_H

#include <string>
namespace mdframework::devices::infrastructure {

struct HomeSystemAddress {
    std::string country;
    std::string city;
    std::string addressLine1;
    std::string addressLine2;
    std::string county;
    std::string postalCode;
};

struct HomeSystemResponse {
    std::string id;
    std::string identityId;
    std::string name;
    std::string description;
    HomeSystemAddress address;
};

}

#endif // MASTERDEVICE_HOMESYSTEMRESPONSE_H
