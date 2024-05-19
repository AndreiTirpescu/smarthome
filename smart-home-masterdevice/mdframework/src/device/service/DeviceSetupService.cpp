#include "DeviceSetupService.h"
#include "device/infrastructure/HomeSystemNetworking.h"
#include "device/infrastructure/MasterDeviceRepository.h"
#include "device/master_device_data.h"
#include "session/session_api.h"

#include <spdlog/spdlog.h>
#include <utility>

mdframework::devices::service::DeviceSetupService::DeviceSetupService(
    MasterDeviceRepositoryPtr repository, HomeSystemNetworkingPtr networking)
    : repository(std::move(repository))
    , networking(networking)
{
}

bool mdframework::devices::service::DeviceSetupService::verifyFirstSetup()
{
    spdlog::info("[DeviceSetupService] Checking if first setup was done before");

    auto result = repository->findAvailable();

    if (result != nullptr) {
        spdlog::info("[DeviceSetupService] Device setup empty");
        return false;
    }

    spdlog::info("[DeviceSetupService] Device setup OK");
    return true;
}

mdframework::devices::master_device_data mdframework::devices::service::DeviceSetupService::setupDevice(
    const std::string& name)
{
    const auto currentSessionData = session::get_current_session();

    const auto response
        = networking->homeSystemsV1Connect(name, currentSessionData.userId, currentSessionData.accessToken);
    const master_device_data data = { response.name, response.id, response.identityId };

    const auto deviceSetupData = repository->store(data);
    spdlog::info(
        "Device setup complete id = {}, identity = {}, name = {}", response.id, response.identityId, response.name);

    return data;
}
