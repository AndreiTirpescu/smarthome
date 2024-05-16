#ifndef MDFRAMEWORKWITHTEST_APPCONFIG_H
#define MDFRAMEWORKWITHTEST_APPCONFIG_H

#include <string>
#include <utility>

class AppConfig {
public:
    AppConfig(std::string dbPath, std::string baseUrl, int port);

    [[nodiscard]] const std::string& dbPath() const;
    [[nodiscard]] const std::string& baseUrl() const;
    [[nodiscard]] int getPort() const;

private:
    std::string _dbPath;
    std::string _baseUrl;
    int _port;
};

#endif // MDFRAMEWORKWITHTEST_APPCONFIG_H
