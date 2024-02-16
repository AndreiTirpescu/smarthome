#ifndef MASTERDEVICE_USERLOGINCOMPONENT_H
#define MASTERDEVICE_USERLOGINCOMPONENT_H

#include <QDebug>
#include <QObject>

class QNetworkAccessManager;

namespace deviceaccess {

class IAccessTokenProvider;

using TokenProviderPtr = std::shared_ptr<IAccessTokenProvider>;

class UserLoginComponent : public QObject {
    Q_OBJECT
public:
    explicit UserLoginComponent(TokenProviderPtr accessTokenProvider);

    Q_INVOKABLE void login(const QString& email, const QString& password);

signals:
    void loginStarted();
    void loginFinished();
    void error(const QString& err);

private:
    TokenProviderPtr accessTokenProvider;
    std::unique_ptr<QNetworkAccessManager> authClient;
};
}

#endif // MASTERDEVICE_USERLOGINCOMPONENT_H
