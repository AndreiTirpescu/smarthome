#ifndef MASTERDEVICE_DEVICESETUPCOMPONENT_H
#define MASTERDEVICE_DEVICESETUPCOMPONENT_H

#include <QGeoPositionInfo>
#include <QObject>

namespace devicesetup {
class DeviceSetupComponent : public QObject {
    Q_OBJECT
public:
    DeviceSetupComponent(QObject* parent);

    Q_INVOKABLE bool checkDeviceSetup();
    Q_INVOKABLE void setupDevice(const QString& deviceName);

signals:
    void setupStarted();
    void setupFinished();
    void error(const QString& err);

private:
    void onPositionReceived(QGeoPositionInfo geoPositionInfo);
};
}

#endif // MASTERDEVICE_DEVICESETUPCOMPONENT_H
