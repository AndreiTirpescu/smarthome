#ifndef MDFRAMEWORK_SESSIONSERVICE_H
#define MDFRAMEWORK_SESSIONSERVICE_H

#include "session/infrastructure/SessionNetworking.h"
#include "session/session_data.h"
#include <memory>

namespace mdframework::session::infrastructure {
class SessionRepository;
}

using SessionRepositoryPtr = std::shared_ptr<mdframework::session::infrastructure::SessionRepository>;
using SessionNetworkingPtr = std::shared_ptr<mdframework::session::infrastructure::SessionNetworking>;

namespace mdframework::session::service {

class SessionService {
public:
    explicit SessionService(SessionRepositoryPtr repository, SessionNetworkingPtr networking);

    session_data loginWithEmailAndPassword(const std::string& email, const std::string& password);

    session_data storeSession(const session_data& sessionData);

private:
    SessionRepositoryPtr repository;
    SessionNetworkingPtr networking;
};
}

#endif // MDFRAMEWORK_SESSIONSERVICE_H
