import { useEffect, useState } from 'react'
import { getDeviceEvents } from '@/smarthome/features/devices/api'

export const useFetchDeviceEvents = (deviceId) => {
    const [deviceData, setDeviceData] = useState({
        deviceEvents: [],
        isLoadingEvents: true
    })

    useEffect(() => {
        (async () => {
            try {
                if (!deviceId) {
                    return
                }
                const resp = await getDeviceEvents(deviceId)

                setDeviceData({
                    deviceEvents: resp.data, isLoadingEvents: false
                })
            } catch (e) {

            }
        })()
    }, [deviceId])

    return { ...deviceData }
}
