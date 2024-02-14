import QtQuick 2.12
import QtQuick.Window 2.12
import QtQuick.Controls 2.12
import QtQuick.Layouts 1.3

import './components' as Components

ApplicationWindow {
    id: mainWindow
    width: 800
    height: 480
    // flags: Qt.FramelessWindowHint
    visible: true
    title: "MasterDevice"

    StackView {
        id: pageContainer
        width: mainWindow.width
        height: mainWindow.height

        initialItem: Rectangle {
            id: randomRectangle
            anchors.fill: parent
            color: "#FFFFFF"

            ColumnLayout {
                spacing: 24
                anchors.centerIn: parent

                Layout.alignment: Qt.AlignCenter

                Components.Input {
                    w: 430
                    h: 48
                    label: "Email"
                }

                Components.Input {
                    w: 430
                    h: 48
                    type: TextInput.Password
                    label: "Password"
                }

                Components.AppButton {
                    label: "Continue"
                    Layout.preferredWidth: 430
                    Layout.preferredHeight: 48
                }
            }

        }
    }
}
