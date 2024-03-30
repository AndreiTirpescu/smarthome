#include "DeviceSetupConfig.h"

#include <utility>

devicesetup::DeviceSetupConfig::DeviceSetupConfig(std::string deviceId, std::string systemName)
    : deviceId(std::move(deviceId))
    , systemName(std::move(systemName))
{
}

std::string devicesetup::DeviceSetupConfig::toString()
{
    return "deviceId = [" + deviceId + "] " + "systemName = [" + systemName + "]";
}
