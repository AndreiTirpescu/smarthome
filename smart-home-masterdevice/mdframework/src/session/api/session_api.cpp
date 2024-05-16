#include "session/session_api.h"
#include "config/locator/ServiceLocator.h"
#include "session/infrastructure/SessionRepository.h"
#include "session/service/SessionService.h"
#include "session/session_data.h"

namespace mdframework::session {

session_data login(const std::string& username, const std::string& password)
{
    using SessionService = mdframework::session::service::SessionService;
    const auto& sessionService = ServiceLocator::instance().resolve<SessionService>();

    return sessionService->loginWithEmailAndPassword(username, password);
}

session_data store_session(const session_data& sessionData)
{
    using SessionService = mdframework::session::service::SessionService;
    const auto& sessionService = ServiceLocator::instance().resolve<SessionService>();

    return sessionService->storeSession(sessionData);
}
}
