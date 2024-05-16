#include "AppConfigLoader.h"
#include "config/model/AppConfig.h"
#include "yaml-cpp/yaml.h"

AppConfig AppConfigLoader::initializeConfig(const std::string& ymlPath)
{
    YAML::Node config = YAML::LoadFile(ymlPath);

    return { config["db_path"].as<std::string>(), config["base_url"].as<std::string>(), config["port"].as<int>() };
}
