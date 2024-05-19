#include "SessionService.h"
#include "config/locator/ServiceLocator.h"
#include "exceptions/exception.h"
#include "session/infrastructure/SessionRepository.h"
#include <jwt-cpp/jwt.h>
#include <spdlog/spdlog.h>
#include <utility>

mdframework::session::service::SessionService::SessionService(
    SessionRepositoryPtr repository, SessionNetworkingPtr networking)
    : repository(std::move(repository))
    , networking(std::move(networking))
{
}

mdframework::session::session_data mdframework::session::service::SessionService::loginWithEmailAndPassword(
    const std::string& email, const std::string& password)
{
    spdlog::info(fmt::format("[SessionService] User with email {}, tries to log in", email));

    const auto authResponse = networking->authV1Login(email, password);
    const auto decoded = jwt::decode(authResponse.accessToken);
    const auto& decodedData = decoded.get_payload_json();

    return { authResponse.accessToken, authResponse.refreshToken, decodedData.at("exp").get<long>(),
        decodedData.at("sub").to_str(), decodedData.at("email").to_str() };
}

mdframework::session::session_data mdframework::session::service::SessionService::storeSession(
    const mdframework::session::session_data& sessionData)
{
    spdlog::info("[SessionService] Storing session data for {}", sessionData.email);

    auto result = repository->findAvailable();

    if (result == nullptr) {
        spdlog::info("[SessionService] No session data found, creating new session");
        const auto newSession = repository->store(sessionData);

        return { newSession->accessToken, newSession->refreshToken, newSession->tokenExpiresAt, newSession->userId,
            newSession->email };
    }

    const auto newSession = repository->updateCurrent(sessionData);
    spdlog::info("[SessionService] Session data found, updating current session");

    return { newSession->accessToken, newSession->refreshToken, newSession->tokenExpiresAt, newSession->userId,
        newSession->email };
}
mdframework::session::session_data mdframework::session::service::SessionService::findCurrentSession()
{
    spdlog::info("[SessionService] Trying to find available session data");

    auto result = repository->findAvailable();

    if (result == nullptr) {
        spdlog::error("[SessionService] No session data was found");
        throw exceptions::InvalidSessionException();
    }

    return { result->accessToken, result->refreshToken, result->tokenExpiresAt, result->userId, result->email };
}
