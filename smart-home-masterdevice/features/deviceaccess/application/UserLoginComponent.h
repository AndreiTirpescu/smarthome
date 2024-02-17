#ifndef MASTERDEVICE_USERLOGINCOMPONENT_H
#define MASTERDEVICE_USERLOGINCOMPONENT_H

#include <QDebug>
#include <QNetworkAccessManager>
#include <QNetworkReply>
#include <QObject>

using NetworkAccessPtr = QNetworkAccessManager*;

namespace deviceaccess {

class IAccessTokenProvider;

using TokenProviderPtr = std::shared_ptr<deviceaccess::IAccessTokenProvider>;

class UserLoginComponent : public QObject {
    Q_OBJECT
public:
    UserLoginComponent(QObject* parent, TokenProviderPtr accessTokenProvider, NetworkAccessPtr networkClient);

    Q_INVOKABLE void login(const QString& email, const QString& password);

signals:
    void loginStarted();
    void loginFinished();
    void error(const QString& err);

private:
    TokenProviderPtr accessTokenProvider;
    NetworkAccessPtr authClient;

    void onNetworkResponse();
    void onNetworkError(QNetworkReply::NetworkError code);
};
}

#endif // MASTERDEVICE_USERLOGINCOMPONENT_H
