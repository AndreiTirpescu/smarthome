import { useEffect, useState } from 'react'
import { getCookie } from 'cookies-next'
import { fetchProfileInfo } from '@/smarthome/features/profile/api'
import { jwtDecode } from 'jwt-decode'

export const useFetchProfileInfo = () => {
    const [profileInfo, setProfileInfo] = useState({
        profileInfo: {},
        isLoading: true
    })

    useEffect(() => {
        (async () => {
            try {
                const { sub } = jwtDecode(getCookie('accessToken'))
                const resp = await fetchProfileInfo({ identityId: sub })
                setProfileInfo({
                    profileInfo: resp.data, isLoading: false
                })
            } catch (e) {

            }
        })()
    }, [])

    return { ...profileInfo }
}
