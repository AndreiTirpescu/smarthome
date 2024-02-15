import QtQuick 6.0
import QtQuick.Window 6.0
import QtQuick.Controls 6.0
import QtQuick.Layouts 6.0
import AppComponents 1.0

Item {
    id: randomRectangle

    anchors.fill: parent
    color: "#FFFFFF"

    ColumnLayout {
        Layout.alignment: Qt.AlignCenter
        anchors.centerIn: parent
        spacing: 24

        Input {
            h: 48
            label: "Email"
            w: 430
        }
        Input {
            h: 48
            label: "Password"
            type: TextInput.Password
            w: 430
        }
        AppButton {
            Layout.preferredHeight: 48
            Layout.preferredWidth: 430
            label: "Continue"
        }
    }
}
