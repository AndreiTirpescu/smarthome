import { useEffect, useState } from 'react'
import { getDeviceById } from '@/smarthome/features/devices/api'

export const useFetchDeviceById = (id) => {
    const [deviceData, setDeviceData] = useState({
        device: [],
        loadingDeviceById: true
    })

    useEffect(() => {
        (async () => {
            try {
                const resp = await getDeviceById(id)
                setDeviceData({
                    device: resp.data, loadingDeviceById: false
                })
            } catch (e) {

            }
        })()
    }, [])

    return { ...deviceData }
}
