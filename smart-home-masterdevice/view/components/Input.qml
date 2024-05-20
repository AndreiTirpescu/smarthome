import QtQuick 6.0
import QtQuick.Window 6.0
import QtQuick.Controls 6.0
import QtQuick.Layouts 6.0


ColumnLayout {
    spacing: 8
    id: root

    property alias label: input_label.text
    property alias text: field.text
    property alias type: field.echoMode

    Text {
        id: input_label

        font.pointSize: 12
        font.weight: 500

        Layout.preferredWidth: parent.width
    }

    TextField {
        id: field

        Layout.preferredWidth: parent.width
        color: "#000"
        font.pointSize: 12
        padding: 4
        Layout.preferredHeight: 48

        background: Rectangle {
            border.color: "#E8E8E8"
            border.width: 1
            color: "#FFFFFF"
            radius: 6
            height: 48
        }
    }
}
