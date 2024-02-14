import QtQuick 2.12
import QtQuick.Window 2.12
import QtQuick.Controls 2.12
import QtQuick.Layouts 1.3

Button {
    id: btn

    property alias w: btn.width
    property alias h: btn.height
    property alias label: buttonName.text

    padding: 4

    Text {
        id: buttonName
        anchors.centerIn: parent
        color: "#FFFFFF"
        font.pointSize: 12
        font.weight: 600
    }

    background: Rectangle {
        color: "#3dabff"
        border.width: 1
        border.color: "#E8E8E8"
        radius: 6
    }
}
