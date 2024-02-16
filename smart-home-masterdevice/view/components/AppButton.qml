import QtQuick 6.0
import QtQuick.Window 6.0
import QtQuick.Controls 6.0

Rectangle {
    id: btn

    property alias h: btn.height
    property alias label: buttonName.text
    property alias w: btn.width

    signal clicked

    border.color: "#E8E8E8"
    border.width: 1
    color: "#3dabff"
    radius: 6

    Text {
        id: buttonName

        anchors.centerIn: parent
        color: "#FFFFFF"
        font.pointSize: 12
        font.weight: 600
        padding: 4
    }
    MouseArea {
        anchors.fill: parent

        onClicked: {
            btn.clicked();
        }
    }
}
