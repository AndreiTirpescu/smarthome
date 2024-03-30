#include "DeviceSetupComponent.h"

#include "DeviceSetupConfig.h"
#include "IDeviceSetupProvider.h"

#include <utility>

devicesetup::DeviceSetupComponent::DeviceSetupComponent(QObject* parent, DeviceSetupProviderPtr deviceSetupProvider)
    : QObject(parent)
    , setupProvider(std::move(deviceSetupProvider))
{
}

bool devicesetup::DeviceSetupComponent::checkSetup()
{
    const auto deviceConfig = setupProvider->currentSetupConfig();
    qDebug() << "Current device setup config" << deviceConfig->toString();

    return deviceConfig->isSetUp();
}
