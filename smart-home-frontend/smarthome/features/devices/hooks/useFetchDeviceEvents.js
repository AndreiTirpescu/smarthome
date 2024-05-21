import { useEffect, useState } from 'react'
import { getDeviceEvents } from '@/smarthome/features/devices/api'

export const useFetchDeviceEvents = (deviceId) => {
    const [deviceData, setDeviceData] = useState({
        events: [],
        loadingEvents: true
    })

    useEffect(() => {
        (async () => {
            try {
                if (!deviceId) {
                    return
                }
                const resp = await getDeviceEvents(deviceId)

                setDeviceData({
                    events: resp.data, isLoadingEvents: false
                })
            } catch (e) {

            }
        })()
    }, [deviceId])

    return { ...deviceData }
}
