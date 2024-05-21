#include "HomeSystemNetworking.h"
#include "exceptions/exception.h"

#include <httplib.h>
#include <nlohmann/json.hpp>
#include <spdlog/spdlog.h>
#include <utility>
#include <vector>

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

mdframework::devices::infrastructure::PagedDevicesResponse
mdframework::devices::infrastructure::HomeSystemNetworking::fetchDevices(const std::string& accessToken)
{
    using json = nlohmann::json;

    httplib::Client client { baseUrl, port };
    client.set_default_headers(
        { { "Authorization", fmt::format(BEARED_TOKEN, accessToken) }, { "Content-Type", CONTENT_TYPE } });
    const auto resp = client.Get(API_V1_DEVICES_FETCH);

    if (resp.error() == httplib::Error::Connection) {
        spdlog::error("Network access error");
        throw exceptions::NetworkAccessError();
    }

    if (resp->status != httplib::OK_200) {
        spdlog::error(fmt::format("Could not fetch devices"));
        throw exceptions::ErrorCreatingHomeSystem();
    }

    spdlog::debug("[Networking] Checked device catalog {}, {}, {}", API_V1_DEVICES_FETCH, resp->body, resp->status);
    json respJson = json::parse(resp->body);

    std::vector<std::string> deviceList {};
    const auto stuff = respJson["devices"];
    std::vector<Device> devices {};
    for (auto it : stuff) {
        devices.push_back({ it["id"].get<std::string>(), it["typeCode"].get<std::string>() });
    }

    return { respJson["count"].get<int>(), respJson["totalPages"].get<int>(), devices };
}

void mdframework::devices::infrastructure::HomeSystemNetworking::homeSystemsV1DevicesConnect(
    const std::string& deviceCatalogId, const std::string& deviceName, const std::string& accessToken,
    const std::string& homeSystemId)
{
    using json = nlohmann::json;

    httplib::Client client { baseUrl, port };
    client.set_default_headers(
        { { "Authorization", fmt::format(BEARED_TOKEN, accessToken) }, { "Content-Type", CONTENT_TYPE } });

    const json body = { { "deviceCatalogId", deviceCatalogId }, { "deviceName", deviceName } };

    spdlog::debug(fmt::format("[Networking] calling {}, host {}, port {}", API_V1_HOME_SYSTEMS_CONNECT, baseUrl, port));

    const auto endpoint = fmt::format("/api/v1/homesystems/{}/devices", homeSystemId);
    auto resp = client.Post(endpoint, nlohmann::to_string(body), CONTENT_TYPE);

    if (resp.error() == httplib::Error::Connection) {
        spdlog::error("Network access error");
        throw exceptions::NetworkAccessError();
    }

    if (resp->status != httplib::OK_200) {
        throw exceptions::ErrorCreatingHomeSystem();
    }

    spdlog::debug("Device typecode {} found and connected", API_V1_HOME_SYSTEMS_CONNECT, resp->body, resp->status);
}
