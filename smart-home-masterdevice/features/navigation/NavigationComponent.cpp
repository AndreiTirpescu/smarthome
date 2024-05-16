#include "NavigationComponent.h"
navigation::NavigationComponent::NavigationComponent(QObject* parent)
    : QObject(parent)
{
}

void navigation::NavigationComponent::changePage(const QString& page) { emit pageChangeRequest(page); }
void navigation::NavigationComponent::goBack() { emit backRequest(); }