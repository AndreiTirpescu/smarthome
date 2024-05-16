#ifndef MASTERDEVICE_USERLOGINCOMPONENT_H
#define MASTERDEVICE_USERLOGINCOMPONENT_H

#include <QDebug>
#include <QNetworkAccessManager>
#include <QNetworkReply>
#include <QObject>

namespace deviceaccess {

class UserLoginComponent : public QObject {
    Q_OBJECT
public:
    UserLoginComponent(QObject* parent);

    Q_INVOKABLE void login(const QString& email, const QString& password);

signals:
    void loginStarted();
    void loginFinished();
    void error(const QString& err);
};
}

#endif // MASTERDEVICE_USERLOGINCOMPONENT_H
