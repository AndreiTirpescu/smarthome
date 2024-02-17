#include "ErrorResponseMapper.h"
#include <QByteArray>
#include <QJsonDocument>

config::dtos::ErrorResponse mappers::ErrorResponseMapper::fromResponse(const QJsonDocument& json)
{
    const ErrorDetails details = []() {
        ErrorDetails errorDetails {};

        return errorDetails;
    }();

    return { json["key"].toString().toStdString(), json["debugMessage"].toString().toStdString(),
        json["status"].toInt(), details };
}
