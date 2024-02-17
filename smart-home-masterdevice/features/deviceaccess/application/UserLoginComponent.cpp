#include "UserLoginComponent.h"
#include <IAccessTokenProvider.h>
#include <QJsonDocument>
#include <QJsonObject>
#include <QNetworkAccessManager>
#include <QNetworkReply>

#include "NetworkRequestBuilder.h"
#include <memory>
#include <utility>

using RequestBuilder = config::NetworkRequestBuilder;

deviceaccess::UserLoginComponent::UserLoginComponent(
    QObject* parent, TokenProviderPtr accessTokenProvider, NetworkAccessPtr networkClient)
    : accessTokenProvider(std::move(accessTokenProvider))
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
    auto resp = authClient->post(request, QJsonDocument(body).toJson());
    connect(resp, &QNetworkReply::readyRead, this, &UserLoginComponent::onAuthResponse);
    
    qDebug() << email << " " << password;
}

void deviceaccess::UserLoginComponent::onAuthResponse()
{
    const auto reply = dynamic_cast<QNetworkReply*>(QObject::sender());
    qDebug() << reply->readAll().toStdString() << "SenderOBJ" << reply;

    reply->deleteLater();
}
