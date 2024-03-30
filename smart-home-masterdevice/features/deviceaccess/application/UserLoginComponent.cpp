#include "UserLoginComponent.h"
#include <IAccessTokenProvider.h>
#include <QIODevice>
#include <QJsonDocument>
#include <QJsonObject>
#include <QNetworkAccessManager>
#include <QNetworkReply>

#include "ErrorResponseMapper.h"
#include "NetworkRequestBuilder.h"
#include <memory>
#include <utility>

using RequestBuilder = config::NetworkRequestBuilder;

deviceaccess::UserLoginComponent::UserLoginComponent(
    QObject* parent, TokenProviderPtr accessTokenProvider, NetworkAccessPtr networkClient)
    : QObject(parent)
    , accessTokenProvider(std::move(accessTokenProvider))
    , authClient(networkClient)
{
}

void deviceaccess::UserLoginComponent::login(const QString& email, const QString& password)
{
    if (email.isEmpty() || password.isEmpty()) {
        emit error("Please enter your credentials");
        return;
    }

    const auto request = RequestBuilder("http://localhost:8080/api/v1/auth/login").build();

    const QJsonObject body = { { "email", email }, { "password", password } };
    auto reply = authClient->post(request, QJsonDocument(body).toJson());

    emit loginStarted();

    connect(reply, &QNetworkReply::finished, this, &UserLoginComponent::onNetworkResponse);
    connect(this, &UserLoginComponent::error, reply, &QObject::deleteLater);
}

void deviceaccess::UserLoginComponent::onNetworkResponse()
{
    const auto reply = dynamic_cast<QNetworkReply*>(QObject::sender());
    const auto rawData = reply->readAll();
    const auto data = QJsonDocument::fromJson(rawData);

    if (reply->error() == QNetworkReply::NetworkError::ConnectionRefusedError) {
        emit error(QString("There was a problem with the server"));

        return;
    }

    if (reply->attribute(QNetworkRequest::HttpStatusCodeAttribute).toInt() != 200) {
        auto errDetails = mappers::ErrorResponseMapper::fromResponse(data);
        emit error(QString(errDetails.key.c_str()));

        return;
    }

    accessTokenProvider->saveToken(
        data["accessToken"].toString().toStdString(), data["refreshToken"].toString().toStdString());

    emit loginFinished();
}

void deviceaccess::UserLoginComponent::onNetworkError(QNetworkReply::NetworkError code)
{
    qDebug() << code;
    emit error(QString("There was a problem with the server"));
}
