#ifndef TEST_SQLITE_USER_H
#define TEST_SQLITE_USER_H

#include <string>

namespace mdframework::session {
class User {
public:
    User(std::string id, std::string email);

    const std::string& id() const;

    const std::string& email() const;

private:
    std::string _id;
    std::string _email;
};
}

#endif // TEST_SQLITE_USER_H
