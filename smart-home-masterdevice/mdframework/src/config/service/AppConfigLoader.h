#ifndef MDFRAMEWORKWITHTEST_APPCONFIGLOADER_H
#define MDFRAMEWORKWITHTEST_APPCONFIGLOADER_H

#include "config/model/AppConfig.h"
#include <string>

class AppConfig;

class AppConfigLoader {
public:
    AppConfig initializeConfig(const std::string& ymlPath);
};

#endif // MDFRAMEWORKWITHTEST_APPCONFIGLOADER_H
