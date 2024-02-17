#ifndef MASTERDEVICE_NETWORKREQUESTBUILDER_H
#define MASTERDEVICE_NETWORKREQUESTBUILDER_H

#include <QNetworkRequest>

class QString;

namespace config {
class NetworkRequestBuilder {

public:
    explicit NetworkRequestBuilder(std::string url);

    NetworkRequestBuilder& authenticated(const QString& accessToken);

    QNetworkRequest build();

private:
    std::string url;
    std::map<std::string, std::string> rawHeaderMap;
};
}

#endif // MASTERDEVICE_NETWORKREQUESTBUILDER_H
