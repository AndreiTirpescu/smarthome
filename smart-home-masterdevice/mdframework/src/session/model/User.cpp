#include "User.h"

mdframework::session::User::User(std::string id, std::string email)
    : _id(std::move(id))
    , _email(std::move(email))
{
}

const std::string& mdframework::session::User::id() const { return _id; }

const std::string& mdframework::session::User::email() const { return _email; }
