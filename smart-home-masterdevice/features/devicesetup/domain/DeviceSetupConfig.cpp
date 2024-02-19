#include "DeviceSetupConfig.h"

#include <utility>
DeviceSetupConfig::DeviceSetupConfig(std::string deviceId, std::string systemName)
    : deviceId(std::move(deviceId))
    , systemName(std::move(systemName))
{
}
