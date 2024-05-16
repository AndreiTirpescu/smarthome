#include "SessionNetworking.h"
#include "exceptions/exception.h"

#include <httplib.h>
#include <nlohmann/json.hpp>
#include <spdlog/spdlog.h>
#include <utility>

mdframework::session::infrastructure::SessionNetworking::SessionNetworking(std::string baseUrl, int port)
    : baseUrl(std::move(baseUrl))
    , port(port)
{
}

LoginResponse mdframework::session::infrastructure::SessionNetworking::authV1Login(
    const std::string& email, const std::string& password)
{
    using json = nlohmann::json;

    httplib::Client client { baseUrl, port };
    const auto body = json { { "email", email }, { "password", password } };

    spdlog::debug(fmt::format(
        "[Networking] calling {}, host {}, port {}, deliberately not showing body data", API_V1_LOGIN, baseUrl, port));

    auto resp = client.Post(API_V1_LOGIN, nlohmann::to_string(body), CONTENT_TYPE);
    if (resp.error() == httplib::Error::Connection) {
        spdlog::error("Network access error");
        throw exceptions::NetworkAccessError();
    }

    if (resp->status != httplib::OK_200) {
        spdlog::error(fmt::format("Invalid credentials for {}, {}", email, resp->body));
        throw exceptions::InvalidCredentialsException();
    }

    spdlog::debug("[Networking] {}, resp: body {}, status {}", API_V1_LOGIN, resp->body, resp->status);
    json resp_json = json::parse(resp->body);

    const auto accessToken = resp_json["accessToken"].get<std::string>();
    const auto refreshToken = resp_json["refreshToken"].get<std::string>();

    return { accessToken, refreshToken };
}
