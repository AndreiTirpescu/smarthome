#include "HomeSystemNetworking.h"
#include "exceptions/exception.h"

#include <httplib.h>
#include <nlohmann/json.hpp>
#include <spdlog/spdlog.h>
#include <utility>

mdframework::devices::infrastructure::HomeSystemNetworking::HomeSystemNetworking(std::string baseUrl, int port)
    : baseUrl(std::move(baseUrl))
    , port(port)
{
}
mdframework::devices::infrastructure::HomeSystemResponse
mdframework::devices::infrastructure::HomeSystemNetworking::homeSystemsV1Connect(
    const std::string& name, const std::string& identityId, const std::string& accessToken)
{
    using json = nlohmann::json;

    httplib::Client client { baseUrl, port };
    client.set_default_headers(
        { { "Authorization", fmt::format(BEARED_TOKEN, accessToken) }, { "Content-Type", CONTENT_TYPE } });

    const json body = { { "name", name }, { "identityId", identityId } };

    spdlog::debug(fmt::format("[Networking] calling {}, host {}, port {}", API_V1_HOME_SYSTEMS_CONNECT, baseUrl, port));

    auto resp = client.Post(API_V1_HOME_SYSTEMS_CONNECT, nlohmann::to_string(body), CONTENT_TYPE);
    if (resp.error() == httplib::Error::Connection) {
        spdlog::error("Network access error");
        throw exceptions::NetworkAccessError();
    }

    if (resp->status != httplib::OK_200) {
        spdlog::error(fmt::format("Could not create homesystem for {}, {}, {}", name, identityId, resp->body));
        throw exceptions::ErrorCreatingHomeSystem();
    }

    spdlog::debug("[Networking] {}, resp: body {}, status {}", API_V1_HOME_SYSTEMS_CONNECT, resp->body, resp->status);
    json respJson = json::parse(resp->body);

    return { respJson["id"].get<std::string>(), respJson["identityId"].get<std::string>(),
        respJson["name"].get<std::string>(), respJson["description"].get<std::string>(), { "", "", "", "", "", "" } };
}
