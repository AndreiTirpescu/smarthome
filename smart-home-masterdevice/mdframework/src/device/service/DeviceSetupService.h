#ifndef MASTERDEVICE_DEVICESETUPSERVICE_H
#define MASTERDEVICE_DEVICESETUPSERVICE_H

#include "device/master_device_data.h"
#include <memory>

namespace mdframework::devices::infrastructure {
class MasterDeviceRepository;
class HomeSystemNetworking;
}

namespace mdframework::devices::service {

using MasterDeviceRepositoryPtr = std::shared_ptr<mdframework::devices::infrastructure::MasterDeviceRepository>;
using HomeSystemNetworkingPtr = std::shared_ptr<mdframework::devices::infrastructure::HomeSystemNetworking>;

class DeviceSetupService {
public:
    explicit DeviceSetupService(MasterDeviceRepositoryPtr repository, HomeSystemNetworkingPtr networking);

    bool verifyFirstSetup();

    mdframework::devices::master_device_data setupDevice(const std::string& name);

    void simulateDeviceConnection();

private:
    MasterDeviceRepositoryPtr repository;
    HomeSystemNetworkingPtr networking;
};

}

#endif // MASTERDEVICE_DEVICESETUPSERVICE_H
