#include "UserLoginComponent.h"
#include <QIODevice>
#include <QJsonDocument>
#include <QJsonObject>

#include "exceptions/exception.h"
#include "session/session_api.h"

deviceaccess::UserLoginComponent::UserLoginComponent(QObject* parent)
    : QObject(parent)
{
}

void deviceaccess::UserLoginComponent::login(const QString& email, const QString& password)
{
    if (email.isEmpty() || password.isEmpty()) {
        emit error("Please enter your credentials");
        return;
    }

    emit loginStarted();
    try {
        const auto sessionData = mdframework::session::login(email.toStdString(), password.toStdString());
        mdframework::session::store_session(sessionData);
        emit loginFinished();
    } catch (const mdframework::exceptions::InvalidCredentialsException& exception) {
        emit error("Invalid credentials");
    } catch (const mdframework::exceptions::NetworkAccessError& exception) {
        emit error("Check your internet connection");
    }
}
