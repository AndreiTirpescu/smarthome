#include "AppConfig.h"

AppConfig::AppConfig(std::string dbPath, std::string baseUrl, int port)
    : _dbPath(std::move(dbPath))
    , _baseUrl(std::move(baseUrl))
    , _port(port)
{
}

int AppConfig::getPort() const { return _port; }
const std::string& AppConfig::baseUrl() const { return _baseUrl; }
const std::string& AppConfig::dbPath() const { return _dbPath; }
