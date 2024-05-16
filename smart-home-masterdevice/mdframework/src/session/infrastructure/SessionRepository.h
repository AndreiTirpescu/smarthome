#ifndef MDFRAMEWORK_SESSIONREPOSITORY_H
#define MDFRAMEWORK_SESSIONREPOSITORY_H

#include <memory>
#include <string>

namespace mdframework::session {
struct session_data;
}

namespace mdframework::session::infrastructure {

class SessionRepository {
public:
    explicit SessionRepository(std::string dbPath);

    std::shared_ptr<session_data> findAvailable();

    std::shared_ptr<session_data> store(const session_data& sessionData);

    std::shared_ptr<session_data> updateCurrent(const session_data& sessionData);

private:
    std::string dbPath;
};

}

#endif // MDFRAMEWORK_SESSIONREPOSITORY_H
