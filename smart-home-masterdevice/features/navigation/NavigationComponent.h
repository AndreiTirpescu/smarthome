#ifndef MASTERDEVICE_NAVIGATIONCOMPONENT_H
#define MASTERDEVICE_NAVIGATIONCOMPONENT_H

#include <QObject>

namespace navigation {
class NavigationComponent : public QObject {
    Q_OBJECT
public:
    NavigationComponent(QObject* parent);

    Q_INVOKABLE void changePage(const QString& page);
    Q_INVOKABLE void goBack();

signals:
    void pageChangeRequest(const QString& page);
    void backRequest();
};
}

#endif // MASTERDEVICE_NAVIGATIONCOMPONENT_H