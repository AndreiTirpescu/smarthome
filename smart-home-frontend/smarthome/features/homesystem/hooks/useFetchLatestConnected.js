import { useEffect, useState } from 'react'
import { getHomeSystemByIdentityId, getLatestConnectedDevices } from '@/smarthome/features/homesystem/api'
import { jwtDecode } from 'jwt-decode'
import { getCookie } from 'cookies-next'

export const useFetchLatestConnected = ({ pageNumber, pageSize }) => {
    const [deviceData, setDeviceData] = useState({
        connectedDevices: [],
        loadingConnectedDevices: true
    })

    useEffect(() => {
        (async () => {
            try {
                const { sub } = jwtDecode(getCookie('accessToken'))
                const homeSystem = await getHomeSystemByIdentityId(sub)
                const resp = await getLatestConnectedDevices(homeSystem.data.id, pageNumber, pageSize)
                console.log(resp.data)
                setDeviceData({
                    connectedDevices: resp.data.devices, loadingConnectedDevices: false
                })
            } catch (e) {

            }
        })()
    }, [])

    return { ...deviceData }
}
