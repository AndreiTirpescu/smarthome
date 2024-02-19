#ifndef MASTERDEVICE_DEVICESETUPCONFIG_H
#define MASTERDEVICE_DEVICESETUPCONFIG_H

#include <string>
class DeviceSetupConfig {

public:
    DeviceSetupConfig(std::string deviceId, std::string systemName);

    bool isDeviceSetUp() { return !deviceId.empty(); }

private:
    std::string deviceId;
    std::string systemName;
};

#endif // MASTERDEVICE_DEVICESETUPCONFIG_H
