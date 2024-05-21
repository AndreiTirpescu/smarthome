#include "device/device_api.h"
#include "config/locator/ServiceLocator.h"
#include "device/service/DeviceSetupService.h"

namespace mdframework::devices {

master_device_data setup_device_with_name(const std::string& name)
{
    using DeviceSetupService = mdframework::devices::service::DeviceSetupService;
    const auto& sessionService = ServiceLocator::instance().resolve<DeviceSetupService>();

    return sessionService->setupDevice(name);
}

bool check_if_first_setup_done()
{
    using DeviceSetupService = mdframework::devices::service::DeviceSetupService;
    const auto& sessionService = ServiceLocator::instance().resolve<DeviceSetupService>();

    return sessionService->verifyFirstSetup();
}

void simulate_device_system_connections()
{
    using DeviceSetupService = mdframework::devices::service::DeviceSetupService;
    const auto& sessionService = ServiceLocator::instance().resolve<DeviceSetupService>();

    sessionService->simulateDeviceConnection();
}
}