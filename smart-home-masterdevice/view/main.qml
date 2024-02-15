import QtQuick 6.0
import QtQuick.Window 6.0
import QtQuick.Controls 6.0
import QtQuick.Layouts 6.0
// import QtQuick.VirtualKeyboard
// import QtQuick.VirtualKeyboard.Settings
import "./components" as Components

ApplicationWindow {
    id: mainWindow

    height: 480
    title: "MasterDevice"
    // flags: Qt.FramelessWindowHint
    visible: true
    width: 800

    Item {
        id: appContents

        anchors.fill: parent

        StackView {
            id: pageContainer

            height: mainWindow.height
            width: mainWindow.width

            initialItem: Rectangle {
                id: randomRectangle

                anchors.fill: parent
                color: "#FFFFFF"

                ColumnLayout {
                    Layout.alignment: Qt.AlignCenter
                    anchors.centerIn: parent
                    spacing: 24

                    Components.Input {
                        h: 48
                        label: "Email"
                        w: 430
                    }
                    Components.Input {
                        h: 48
                        label: "Password"
                        type: TextInput.Password
                        w: 430
                    }
                    Components.AppButton {
                        Layout.preferredHeight: 48
                        Layout.preferredWidth: 430
                        label: "Continue"
                    }
                }
            }
        }
    }
}
