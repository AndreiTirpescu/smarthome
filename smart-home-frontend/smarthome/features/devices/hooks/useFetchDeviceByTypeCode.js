import { useEffect, useState } from 'react'
import { getDeviceByTypeCode } from '@/smarthome/features/devices/api'

export const useFetchDeviceByTypeCode = ({ typeCode }) => {
    const [deviceData, setDeviceData] = useState({
        device: {},
        isLoading: true
    })

    useEffect(() => {
        (async () => {
            try {
                const resp = await getDeviceByTypeCode(typeCode)

                setDeviceData({
                    device: resp.data, isLoading: false
                })
            } catch (e) {

            }
        })()
    }, [])

    return { ...deviceData }
}
