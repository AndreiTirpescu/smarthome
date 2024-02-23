import { useState, useEffect } from 'react'
import { getCookie } from 'cookies-next'
import { jwtDecode } from 'jwt-decode'

export const useSession = () => {
    const [currentSession, setCurrentSession] = useState(null)

    useEffect(() => {
        const accessToken = getCookie('accessToken')
        const { ...session } = jwtDecode(accessToken)
        setCurrentSession(session)
    }, [])

    return currentSession
}
