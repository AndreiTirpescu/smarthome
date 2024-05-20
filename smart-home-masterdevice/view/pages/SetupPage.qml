import QtQuick 6.0
import QtQuick.Window 6.0
import QtQuick.Controls 6.0
import QtQuick.Layouts 6.0
import components 1.0
import device.setup 1.0
import navigation 1.0

Item {
    id: randomRectangle

    RowLayout {
        anchors.fill: parent
        spacing: 0
        visible: !loading.visible

        Image {
            source: "/assets/login_image.jpg"
            Layout.preferredWidth: 0.45 * parent.width
            Layout.fillHeight: true
            fillMode: Image.PreserveAspectCrop
            smooth: true
            mipmap: true
            clip: true
        }

        Item {
            id: loginInputWrapper

            Layout.preferredWidth: 0.55 * parent.width - 24
            Layout.fillHeight: true

            ColumnLayout {
                id: inputLayout
                anchors.centerIn: parent
                width: parent.width
                spacing: 32

                Text {
                    id: loginLabel

                    font.pointSize: 24
                    font.weight: 300
                    text: "Let's set up your device!"
                }

                Input {
                    id: name
                    label: "Home system name"
                    Layout.preferredWidth: parent.width
                }

                Text {
                    id: errorLabel

                    Layout.alignment: Qt.AlignVCenter | Qt.AlignHCenter
                    color: "#FF0000"
                    font.pointSize: 12
                    font.weight: 500
                    visible: text !== undefined
                }
                AppButton {
                    Layout.preferredWidth: parent.width
                    label: "Continue"

                    onClicked: {
                        DeviceSetup.setupDevice(name.text);
                    }
                }
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
            color: "#9038FF"
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