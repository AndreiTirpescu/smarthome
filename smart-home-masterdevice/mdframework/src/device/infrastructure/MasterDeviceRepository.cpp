#include "MasterDeviceRepository.h"
#include "device/master_device_data.h"
#include "exceptions/exception.h"

#include <SQLiteCpp/Database.h>
#include <spdlog/spdlog.h>
#include <utility>

using master_device_data = mdframework::devices::master_device_data;

mdframework::devices::infrastructure::MasterDeviceRepository::MasterDeviceRepository(std::string dbPath)
    : dbPath(std::move(dbPath))
{
}

std::shared_ptr<master_device_data> mdframework::devices::infrastructure::MasterDeviceRepository::findAvailable()
{
    const SQLite::Database db(dbPath, SQLite::OPEN_READWRITE);
    SQLite::Statement query(db, "SELECT name, home_system_id, identity_id FROM master_device WHERE id = 1");

    int result = query.executeStep();

    if (!result) {
        spdlog::info("[MasterDeviceRepository] No master_device data available, check initial setup");
        return nullptr;
    }

    return std::make_shared<master_device_data>(
        query.getColumn(0).getString(), query.getColumn(1).getString(), query.getColumn(2).getString());
}

std::shared_ptr<master_device_data> mdframework::devices::infrastructure::MasterDeviceRepository::store(
    const master_device_data& masterDeviceData)
{
    const SQLite::Database db(dbPath, SQLite::OPEN_READWRITE);
    SQLite::Statement saveDeviceConfig(
        db, "INSERT INTO master_device (name, home_system_id, identity_id) VALUES(?, ?, ?)");

    saveDeviceConfig.bind(1, masterDeviceData.name);
    saveDeviceConfig.bind(2, masterDeviceData.homeSystemId);
    saveDeviceConfig.bind(3, masterDeviceData.identityId);

    try {
        saveDeviceConfig.exec();
    } catch (const SQLite::Exception& exception) {
        spdlog::error(fmt::format(
            "[MasterDeviceRepository] Exception during the execution fo storedata: [{}]", db.getErrorMsg()));
        throw exceptions::StorageException();
    }

    return std::make_shared<master_device_data>(
        masterDeviceData.name, masterDeviceData.homeSystemId, masterDeviceData.identityId);
}
