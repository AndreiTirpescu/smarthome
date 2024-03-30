#ifndef MASTERDEVICE_IDEVICESETUPPROVIDER_H
#define MASTERDEVICE_IDEVICESETUPPROVIDER_H

namespace devicesetup {
class DeviceSetupConfig;
}

namespace devicesetup {
class IDeviceSetupProvider {

public:
    virtual std::shared_ptr<DeviceSetupConfig> currentSetupConfig() = 0;
};
}

#endif // MASTERDEVICE_IDEVICESETUPPROVIDER_H
