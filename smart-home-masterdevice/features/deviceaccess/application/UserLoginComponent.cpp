#include "UserLoginComponent.h"
#include <IAccessTokenProvider.h>
#include <QJsonDocument>
#include <QJsonObject>
#include <QNetworkAccessManager>

deviceaccess::UserLoginComponent::UserLoginComponent(deviceaccess::TokenProviderPtr accessTokenProvider)
    : accessTokenProvider(std::move(accessTokenProvider))
    , authClient(std::make_unique<QNetworkAccessManager>(this))
{
}

void deviceaccess::UserLoginComponent::login(const QString& email, const QString& password)
{
    if (email.isEmpty() || password.isEmpty()) {
        emit error("Please enter your credentials");
        return;
    }

    QNetworkRequest request { QUrl("http://localhost:8080/api/v1/auth/login") };
    const QJsonObject body = { { "email", email }, { "password", password } };
    authClient->post(request, QJsonDocument(body).toJson());
    
    qDebug() << email << " " << password;
}
