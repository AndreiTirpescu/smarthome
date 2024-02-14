import QtQuick 2.12
import QtQuick.Window 2.12
import QtQuick.Controls 2.12
import QtQuick.Layouts 1.3

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
                spacing: 8
                anchors.centerIn: parent

                TextField {
                    Layout.preferredWidth: 430
                    Layout.preferredHeight: 48
                    padding: 4
                    background: Rectangle {
                        color: "#FFFFFF"
                        border.width: 1
                        border.color: "#E8E8E8"
                        radius: 6
                    }
                }

                TextField {
                    Layout.preferredWidth: 430
                    Layout.preferredHeight: 48
                    padding: 4
                    background: Rectangle {
                        color: "#FFFFFF"
                        border.width: 1
                        border.color: "#E8E8E8"
                        radius: 6
                    }
                }

                Button {
                    Layout.preferredWidth: 430
                    Layout.preferredHeight: 48
                    padding: 4

                    Text {
                        id: label
                        text: qsTr("Continue")
                        anchors.centerIn: parent
                        color: "#FFFFFF"
                    }

                    background: Rectangle {
                        color: "#3dabff"
                        border.width: 1
                        border.color: "#E8E8E8"
                        radius: 6
                    }
                }
            }

        }
    }
}
