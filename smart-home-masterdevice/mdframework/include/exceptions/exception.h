#ifndef MDFRAMEWORK_EXCEPTIONS
#define MDFRAMEWORK_EXCEPTIONS

#include <stdexcept>

namespace mdframework::exceptions {
class exception : public std::runtime_error {
public:
    explicit exception(std::string message);

    [[nodiscard]] const char* what() const noexcept override;

private:
    std::string message;
};

class InvalidCredentialsException : public exception {
public:
    InvalidCredentialsException();
};

class NetworkAccessError : public exception {
public:
    NetworkAccessError();
};
class StorageException : public exception {
public:
    StorageException();
};

}

#endif