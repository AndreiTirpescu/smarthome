#ifndef MASTERDEVICE_ERRORRESPONSEMAPPER_H
#define MASTERDEVICE_ERRORRESPONSEMAPPER_H

#include "ErrorResponse.h"

using ErrorResponse = config::dtos::ErrorResponse;
using ErrorDetails = std::map<std::string, std::string>;

class QJsonDocument;

namespace mappers {
class ErrorResponseMapper {
public:
    static ErrorResponse fromResponse(const QJsonDocument& json);
};
}

#endif // MASTERDEVICE_ERRORRESPONSEMAPPER_H
