import QtQuick 2.12
import QtQuick.Window 2.12
import QtQuick.Controls 2.12



Column {
    id: root
    spacing: 8

    property alias w: field.width
    property alias h: field.height
    property alias type: field.echoMode
    property alias label: input_label.text

    Text {
        id: input_label
        font.weight: 500
        font.pointSize: 10.5
    }

    TextField {
        id: field
        padding: 4
        width: w
        height: h
        font.pointSize: 12

        background: Rectangle {
            color: "#FFFFFF"
            border.width: 1
            border.color: "#E8E8E8"
            radius: 6
        }
    }
}
