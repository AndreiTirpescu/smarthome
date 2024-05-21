#ifndef MASTERDEVICE_DEVICESRESPONSE_H
#define MASTERDEVICE_DEVICESRESPONSE_H

#include <string>
#include <vector>

namespace mdframework::devices::infrastructure {

struct Device {
    std::string id;
    std::string typeCode;
};

struct PagedDevicesResponse {
    int count;
    int totalPages;
    std::vector<Device> devices;
};

}

#endif // MASTERDEVICE_DEVICESRESPONSE_H
