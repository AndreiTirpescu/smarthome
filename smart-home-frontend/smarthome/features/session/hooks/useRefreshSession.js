import { getCookie } from 'cookies-next'
import { refresh } from '@/smarthome/features/session/api'

export const useRefreshSession = () => {
    return async () => {
        const refreshToken = getCookie('refreshToken')

        return await refresh({ refreshToken })
    }
}
