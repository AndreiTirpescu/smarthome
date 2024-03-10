import { useEffect, useState } from 'react'
import { getDevicesPaged } from '@/smarthome/features/devices/api'

export const useFetchDevices = ({ pageNumber, pageSize }) => {
    const [deviceData, setDeviceData] = useState({
        devices: [],
        isLoading: true
    })

    useEffect(() => {
        (async () => {
            try {
                const resp = await getDevicesPaged({ pageNumber, pageSize })

                setDeviceData({
                    devices: resp.data.devices, isLoading: false
                })
            } catch (e) {

            }
        })()
    }, [])

    return { ...deviceData }
}
