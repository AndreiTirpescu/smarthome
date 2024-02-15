import QtQuick 6.0
import QtQuick.Window 6.0
import QtQuick.Controls 6.0
import QtQuick.Layouts 6.0
import "./pages" as Pages

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

            initialItem: LoginPage {
            }
        }
    }
}
