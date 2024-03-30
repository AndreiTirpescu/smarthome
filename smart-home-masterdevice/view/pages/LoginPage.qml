import QtQuick 6.0
import QtQuick.Window 6.0
import QtQuick.Controls 6.0
import QtQuick.Layouts 6.0
import AppComponents 1.0
import device.access 1.0
import navigation 1.0

Page {
    id: randomRectangle

    signal loginSuccess

    anchors.fill: parent

    background: Rectangle {
        color: "#FFFFFF"
    }

    ColumnLayout {
        Layout.alignment: Qt.AlignCenter
        anchors.centerIn: parent
        spacing: 24
        visible: !loading.visible

        Input {
            id: email

            h: 48
            label: "Email"
            w: 430
        }
        Input {
            id: password

            h: 48
            label: "Password"
            type: TextInput.Password
            w: 430
        }
        Text {
            id: error_label

            Layout.alignment: Qt.AlignVCenter | Qt.AlignHCenter
            color: "#FF0000"
            font.pointSize: 10.5
            font.weight: 500
            visible: text !== undefined
        }
        AppButton {
            Layout.preferredHeight: 48
            Layout.preferredWidth: 430
            label: "Continue"

            onClicked: {
                UserLogin.login(email.text, password.text);
                Navigator.changePage('qrc:///AppPages/HomePage.qml');
            }
        }
    }
    Rectangle {
        id: loading

        anchors.fill: parent
        color: "#FF0000"
        visible: false
    }
    Connections {
        function onError(errStr) {
            error_label.text = errStr;
            loading.visible = false;
        }
        function onLoginFinished() {
            loading.visible = true;
        }
        function onLoginStarted() {
            loading.visible = true;
        }

        target: UserLogin
    }
}
