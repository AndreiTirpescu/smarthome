import { useEffect, useState } from 'react'
import { getDeviceValues } from '@/smarthome/features/devices/api'

export const useFetchDeviceValues = (deviceId) => {
    const [deviceData, setDeviceData] = useState({
        deviceValues: [],
        isLoadingValues: true
    })

    useEffect(() => {
        (async () => {
            try {
                if (!deviceId) {
                    return
                }
                const resp = await getDeviceValues(deviceId)

                setDeviceData({
                    deviceValues: resp.data, isLoadingValues: false
                })
            } catch (e) {

            }
        })()
    }, [deviceId])

    return { ...deviceData }
}
