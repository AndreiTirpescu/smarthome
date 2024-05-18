import QtQuick 6.0
import QtQuick.Window 6.0
import QtQuick.Controls 6.0
import QtQuick.Layouts 6.0
import QtQuick.VirtualKeyboard
import components 1.0
import navigation 1.0

ApplicationWindow {
    id: mainWindow

    height: 480
    title: "MasterDevice"
    // flags: Qt.FramelessWindowHint
    visible: true
    width: 800

    background: Rectangle {
        color: "#FFFFFF"
    }


    Flickable {
        width: parent.width
        height: parent.height
        contentWidth: parent.width
        contentHeight: parent.height
        flickableDirection: Flickable.VerticalFlick

        Loader {
            id: pageLoader
            anchors.centerIn: parent
            width: parent.width
            height: parent.height

            Connections {
                function onPageChangeRequest(page) {
                    pageLoader.source = page
                }

                target: Navigator
            }
        }
    }

    InputPanel {
        id: virtualKeyboard
        z: 99
        x: 0
        y: mainWindow.height
        width: mainWindow.width

        states: State {
            name: "visible"
            when: virtualKeyboard.active
            PropertyChanges {
                target: virtualKeyboard
                y: mainWindow.height - virtualKeyboard.height
            }
        }

        transitions: Transition {
            from: ""
            to: "visible"
            reversible: true
            ParallelAnimation {
                NumberAnimation {
                    properties: "y"
                    duration: 250
                    easing.type: Easing.InOutQuad
                }
            }
        }
    }

    Component.onCompleted: function () {
        pageLoader.source = "/pages/LoginPage.qml"
    }
}
