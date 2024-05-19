#include "exceptions/exception.h"

mdframework::exceptions::Exception::Exception(std::string message)
    : message(std::move(message))
    , std::runtime_error(message)
{
}
const char* mdframework::exceptions::Exception::what() const noexcept { return message.c_str(); }

mdframework::exceptions::InvalidCredentialsException::InvalidCredentialsException()
    : Exception("Invalid user credentials")
{
}
mdframework::exceptions::NetworkAccessError::NetworkAccessError()
    : Exception("Network access error")
{
}
mdframework::exceptions::StorageException::StorageException()
    : Exception("Storage Exception")
{
}
mdframework::exceptions::InvalidSessionException::InvalidSessionException()
    : Exception("Invalid session exception, try to reconnect")
{
}
mdframework::exceptions::ErrorCreatingHomeSystem::ErrorCreatingHomeSystem()
    : Exception("Could not create home system, please try again later")
{
}
