import { useEffect, useState } from 'react'
import { getAllUsers } from '@/smarthome/features/users/api'

export const useFetchUsers = ({ pageNumber, pageSize }) => {
    const [userData, setUserData] = useState({
        users: [],
        isLoading: true
    })

    useEffect(() => {
        (async () => {
            try {
                const resp = await getAllUsers({ pageNumber, pageSize })
                setUserData({
                    users: resp.data.users, isLoading: false
                })
            } catch (e) {

            }
        })()
    }, [])

    return { ...userData }
}
