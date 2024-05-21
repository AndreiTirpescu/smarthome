import { useEffect, useState } from 'react'
import { getDeviceValues } from '@/smarthome/features/devices/api'

export const useFetchDeviceValues = (deviceId) => {
    const [deviceData, setDeviceData] = useState({
        values: [],
        loadingValues: true
    })

    useEffect(() => {
        (async () => {
            try {
                if (!deviceId) {
                    return
                }
                const resp = await getDeviceValues(deviceId)

                setDeviceData({
                    values: resp.data, loadingValues: false
                })
            } catch (e) {

            }
        })()
    }, [deviceId])

    return { ...deviceData }
}
