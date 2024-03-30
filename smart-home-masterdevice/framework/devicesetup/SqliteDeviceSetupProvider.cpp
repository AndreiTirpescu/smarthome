#include "SqliteDeviceSetupProvider.h"

#include "DbConnection.h"
#include "DeviceSetupConfig.h"
#include <QSqlQuery>
#include <utility>

framework::SqliteDeviceSetupProvider::SqliteDeviceSetupProvider(DbConnectionPtr dbConnection)
    : dbConnection(std::move(dbConnection))
{
}

framework::DeviceSetupConfigPtr framework::SqliteDeviceSetupProvider::currentSetupConfig()
{
    QSqlQuery fetchQuery = dbConnection->query("SELECT device_id, system_name FROM tokens LIMIT 1");

    fetchQuery.exec();

    if (!fetchQuery.first()) {
        return std::make_shared<DeviceSetupConfig>("", "");
    }

    return std::make_shared<DeviceSetupConfig>(fetchQuery.value("device_id").toString().toStdString(),
        fetchQuery.value("system_name").toString().toStdString());
}
