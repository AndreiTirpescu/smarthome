#ifndef MASTERDEVICE_SQLITEDEVICESETUPPROVIDER_H
#define MASTERDEVICE_SQLITEDEVICESETUPPROVIDER_H

#include <memory>

#include "IDeviceSetupProvider.h"

namespace config {
class DbConnection;
}

namespace devicesetup {
class DeviceSetupConfig;
}

namespace framework {
using DbConnectionPtr = std::shared_ptr<config::DbConnection>;
using DeviceSetupConfigPtr = std::shared_ptr<devicesetup::DeviceSetupConfig>;
using DeviceSetupConfig = devicesetup::DeviceSetupConfig;

class SqliteDeviceSetupProvider : public devicesetup::IDeviceSetupProvider {
public:
    explicit SqliteDeviceSetupProvider(DbConnectionPtr dbConnection);

    DeviceSetupConfigPtr currentSetupConfig() override;

private:
    DbConnectionPtr dbConnection;
};

}

#endif // MASTERDEVICE_SQLITEDEVICESETUPPROVIDER_H
