//
// Created by andrei on 01.04.2024.
//

#include "Device.h"

#include <utility>

mdframework::device::Device::Device(std::string id, std::string systemName)
    : _id(std::move(id))
    , _systemName(std::move(systemName))
{
}

const std::string& mdframework::device::Device::id() const { return _id; }

const std::string& mdframework::device::Device::systemName() const { return _systemName; }
