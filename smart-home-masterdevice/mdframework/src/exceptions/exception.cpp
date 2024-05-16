#include "exceptions/exception.h"

mdframework::exceptions::exception::exception(std::string message)
    : message(std::move(message))
    , std::runtime_error(message)
{
}
const char* mdframework::exceptions::exception::what() const noexcept { return message.c_str(); }

mdframework::exceptions::InvalidCredentialsException::InvalidCredentialsException()
    : exception("Invalid user credentials")
{
}
mdframework::exceptions::NetworkAccessError::NetworkAccessError()
    : exception("Network access error")
{
}
mdframework::exceptions::StorageException::StorageException()
    : exception("Storage exception")
{
}
