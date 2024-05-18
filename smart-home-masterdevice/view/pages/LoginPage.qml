import QtQuick 6.0
import QtQuick.Window 6.0
import QtQuick.Controls 6.0
import QtQuick.Layouts 6.0
import AppComponents 1.0
import device.access 1.0
import navigation 1.0

Item {
    id: randomRectangle

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
            id: errorLabel

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
            }
        }
    }

    ColumnLayout {
        id: loading
        visible: false
        anchors.centerIn: parent

        Spinner {
            id: loadingSpinner
            spinnerWidth: 200
            spinnerHeight: 200
            Layout.alignment: Qt.AlignVCenter | Qt.AlignHCenter
        }

        Text {
            id: loadingText
            text: "Connecting your home device..."
            color: "#3dabff"
        }
    }

    Timer {
        id: refreshTimer
        interval: 2500
        repeat: false

        function delay(callBack) {
            refreshTimer.triggered.connect(callBack)
            refreshTimer.start();
        }
    }

    Connections {
        function onError(errStr) {
            errorLabel.text = errStr
            loading.visible = false
        }

        function onLoginFinished() {
            refreshTimer.delay(function () {
                Navigator.changePage('qrc:///AppPages/HomePage.qml')
            })
        }

        function onLoginStarted() {
            loading.visible = true
        }

        target: UserLogin
    }
}
