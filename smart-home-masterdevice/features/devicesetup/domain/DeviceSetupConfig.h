#ifndef MASTERDEVICE_DEVICESETUPCONFIG_H
#define MASTERDEVICE_DEVICESETUPCONFIG_H

#include <string>
namespace devicesetup {
class DeviceSetupConfig {

public:
    DeviceSetupConfig(std::string deviceId, std::string systemName);

    bool isSetUp() { return !deviceId.empty() && !systemName.empty(); }

    std::string toString();

private:
    std::string deviceId;
    std::string systemName;
};
}

#endif // MASTERDEVICE_DEVICESETUPCONFIG_H
