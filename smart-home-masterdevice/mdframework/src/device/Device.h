#ifndef TEST_SQLITE_DEVICE_H
#define TEST_SQLITE_DEVICE_H

#include <string>

namespace mdframework::device {
class Device {
public:
    Device(std::string id, std::string systemName);

    [[nodiscard]] const std::string& id() const;

    [[nodiscard]] const std::string& systemName() const;

private:
    std::string _id;
    std::string _systemName;
};
}

#endif // TEST_SQLITE_DEVICE_H
