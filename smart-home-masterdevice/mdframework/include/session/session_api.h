#ifndef MDFRAMEWORK_SESSION_MANAGEMENT_H
#define MDFRAMEWORK_SESSION_MANAGEMENT_H

#include "session_data.h"
#include <string>

namespace mdframework::session {
session_data login(const std::string& username, const std::string& password);
session_data store_session(const session_data& sessionData);
session_data get_current_session();
}

#endif // MDFRAMEWORK_SESSION_MANAGEMENT_H
