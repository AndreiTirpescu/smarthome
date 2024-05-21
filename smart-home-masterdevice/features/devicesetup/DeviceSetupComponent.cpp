#include "DeviceSetupComponent.h"
#include "device/device_api.h"
#include "exceptions/exception.h"
#include <QGeoCoordinate>
#include <QGeoPositionInfoSource>

devicesetup::DeviceSetupComponent::DeviceSetupComponent(QObject* parent)
    : QObject(parent)
{
}

bool devicesetup::DeviceSetupComponent::checkDeviceSetup() { return mdframework::devices::check_if_first_setup_done(); }

void devicesetup::DeviceSetupComponent::setupDevice(const QString& deviceName)
{
    emit setupStarted();
    try {
        mdframework::devices::setup_device_with_name(deviceName.toStdString());
    } catch (const mdframework::exceptions::Exception& exception) {
        emit error(exception.what());
        return;
    }
    emit setupFinished();
}

void devicesetup::DeviceSetupComponent::simulateDeviceConnectivity()
{
    try {
        mdframework::devices::simulate_device_system_connections();
    } catch (const mdframework::exceptions::Exception& exception) {
        emit error(exception.what());
        return;
    }
}
