#ifndef MDFRAMEWORK_DEVICE_API_H
#define MDFRAMEWORK_DEVICE_API_H

#include "master_device_data.h"

namespace mdframework::devices {
[[maybe_unused]] bool check_if_first_setup_done();
[[maybe_unused]] master_device_data setup_device_with_name(const std::string& name);
}

#endif