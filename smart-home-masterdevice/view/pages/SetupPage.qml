import QtQuick 6.0
import QtQuick.Window 6.0
import QtQuick.Controls 6.0
import QtQuick.Layouts 6.0
import components 1.0
import device.setup 1.0
import navigation 1.0

Item {
    id: randomRectangle

    ColumnLayout {
        Layout.alignment: Qt.AlignCenter
        anchors.centerIn: parent
        spacing: 24
        visible: !loading.visible

        Text {
            id: setupText
            text: "Set up your device"
            Layout.alignment: Qt.AlignHCenter
        }

        Input {
            id: name

            h: 48
            label: "Device name"
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
                DeviceSetup.setupDevice(name.text);
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
            text: "Setting up your home device..."
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

        function onSetupFinished() {
            refreshTimer.delay(function () {
                Navigator.changePage('/pages/HomePage.qml')
            })
        }

        function onSetupStarted() {
            loading.visible = true
        }

        target: DeviceSetup
    }
}