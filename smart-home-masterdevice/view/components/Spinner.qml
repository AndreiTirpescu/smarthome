import QtQuick 6.0
import QtQuick.Window 6.0
import QtQuick.Controls 6.0

Item {
    id: spinner

    property alias spinnerHeight: spinner.height
    property alias spinnerWidth: spinner.width

    Image {
        source: "/assets/spinner.svg"
        anchors.fill: parent
        smooth: true
        mipmap: true
    }

    RotationAnimator {
        target: spinner;
        loops: Animation.Infinite
        to: 360;
        duration: 1000
        running: true
    }
}