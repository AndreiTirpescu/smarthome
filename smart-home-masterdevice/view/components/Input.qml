import QtQuick 6.0
import QtQuick.Window 6.0
import QtQuick.Controls 6.0

Column {
    id: root

    property alias h: field.height
    property alias label: input_label.text
    property alias type: field.echoMode
    property alias w: field.width

    spacing: 8

    Text {
        id: input_label

        font.pointSize: 10.5
        font.weight: 500
    }
    TextField {
        id: field

        color: "#000"
        font.pointSize: 12
        height: h
        padding: 4
        width: w

        background: Rectangle {
            border.color: "#E8E8E8"
            border.width: 1
            color: "#FFFFFF"
            radius: 6
        }
    }
}
