#include "NetworkRequestBuilder.h"

#include <utility>
config::NetworkRequestBuilder::NetworkRequestBuilder(std::string url)
    : url(std::move(url))
    , rawHeaderMap({})
{
    rawHeaderMap["Content-Type"] = "application/json";
}

config::NetworkRequestBuilder& config::NetworkRequestBuilder::authenticated(const QString& accessToken)
{
    rawHeaderMap["Authorization"] = QString("%1 %2").arg("Bearer", accessToken).toStdString();

    return *this;
}

QNetworkRequest config::NetworkRequestBuilder::build()
{
    QNetworkRequest request { QUrl(url.c_str()) };
    for (const auto& [key, pair] : rawHeaderMap) {
        request.setRawHeader(key.c_str(), pair.c_str());
    }

    return request;
}
