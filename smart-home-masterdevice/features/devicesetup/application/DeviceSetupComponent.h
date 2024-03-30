#ifndef MASTERDEVICE_DEVICESETUPCOMPONENT_H
#define MASTERDEVICE_DEVICESETUPCOMPONENT_H
#include <QDebug>
#include <QNetworkAccessManager>
#include <QNetworkReply>
#include <QObject>

namespace devicesetup {
class IDeviceSetupProvider;
}

namespace devicesetup {

using DeviceSetupProviderPtr = std::shared_ptr<devicesetup::IDeviceSetupProvider>;

class DeviceSetupComponent : public QObject {
    Q_OBJECT
public:
    DeviceSetupComponent(QObject* parent, DeviceSetupProviderPtr deviceSetupProvider);

    Q_INVOKABLE bool checkSetup();

private:
    DeviceSetupProviderPtr setupProvider;
};

}

#endif // MASTERDEVICE_DEVICESETUPCOMPONENT_H
