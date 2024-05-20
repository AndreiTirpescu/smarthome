import QtQuick 6.0
import QtQuick.Window 6.0
import QtQuick.Controls 6.0

Item {
    id: btn

    property alias label: buttonName.text;
    height: 48

        signal
    clicked

    Rectangle {
        id: background
        anchors.fill: parent
        border.color: "#E8E8E8"
        border.width: 1
        color: "#9038FF"
        radius: 6
        height: parent.height
    }

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
