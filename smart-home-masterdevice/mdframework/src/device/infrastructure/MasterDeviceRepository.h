#ifndef MASTERDEVICE_MASTERDEVICEREPOSITORY_H
#define MASTERDEVICE_MASTERDEVICEREPOSITORY_H

#include <memory>
#include <string>

namespace mdframework::devices {
struct master_device_data;
}

namespace mdframework::devices::infrastructure {
class MasterDeviceRepository {
public:
    explicit MasterDeviceRepository(std::string dbPath);

    std::shared_ptr<master_device_data> findAvailable();

    std::shared_ptr<master_device_data> store(const master_device_data& masterDeviceData);

private:
    std::string dbPath;
};
}

#endif // MASTERDEVICE_MASTERDEVICEREPOSITORY_H
