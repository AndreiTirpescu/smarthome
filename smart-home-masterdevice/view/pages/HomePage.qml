import QtQuick 6.0
import QtQuick.Window 6.0
import QtQuick.Controls 6.0
import QtQuick.Layouts 6.0
import components 1.0
import device.setup 1.0

Item {
    anchors.fill: parent

    Image {
        source: "/assets/home_page_cover.jpg"
        anchors.fill: parent
        fillMode: Image.PreserveAspectCrop
        smooth: true
        mipmap: true
        clip: true
    }

    ColumnLayout {
        height: parent.height
        width: parent.width
        anchors.centerIn: parent
        spacing: 16

        RowLayout {
            Layout.preferredWidth: parent.width
            Layout.fillHeight: false
            Layout.alignment: Qt.AlignTop | Qt.AlignHCenter
            Layout.margins: 16

            Text {
                id: homeSystemLabel
                text: "Nicolina Home"
                font.pointSize: 18
                Layout.alignment: Qt.AlignLeft | Qt.AlignVCenter
            }
            ColumnLayout {
                id: dateTimeLayout
                spacing: 4
                Layout.alignment: Qt.AlignRight | Qt.AlignVCenter

                Text {
                    id: timeStampLabel
                    text: "10:23"
                    font.pointSize: 18
                    font.weight: 500
                }
                Text {
                    id: timeStampLabelDay
                    text: "Mon, May 20th 2024"
                    font.pointSize: 12
                    font.weight: 500
                }
            }
        }
    }

    Timer {
        id: refreshTimer
        interval: 500
        repeat: false

        function delay(callBack) {
            refreshTimer.triggered.connect(callBack)
            refreshTimer.start();
        }
    }

    Component.onCompleted: function () {
        refreshTimer.delay(function () {
            DeviceSetup.simulateDeviceConnectivity()
        })
    }
}