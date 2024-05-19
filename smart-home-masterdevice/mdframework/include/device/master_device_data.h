#ifndef MDFRAMEWORK_DEVICE_DATA_H
#define MDFRAMEWORK_DEVICE_DATA_H

#include <string>

namespace mdframework::devices {

struct master_device_data {
    std::string name;
    std::string homeSystemId;
    std::string identityId;
};

}

#endif