#ifndef MDFRAMEWORK_EXCEPTIONS
#define MDFRAMEWORK_EXCEPTIONS

#include <stdexcept>

namespace mdframework::exceptions {
class Exception : public std::runtime_error {
public:
    explicit Exception(std::string message);

    [[nodiscard]] const char* what() const noexcept override;

private:
    std::string message;
};

class InvalidCredentialsException : public Exception {
public:
    InvalidCredentialsException();
};

class NetworkAccessError : public Exception {
public:
    NetworkAccessError();
};
class StorageException : public Exception {
public:
    StorageException();
};
class InvalidSessionException : public Exception {
public:
    InvalidSessionException();
};
class ErrorCreatingHomeSystem : public Exception {
public:
    ErrorCreatingHomeSystem();
};

}

#endif