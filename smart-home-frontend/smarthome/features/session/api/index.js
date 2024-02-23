import { backend } from '@/smarthome/lib'
import axios from 'axios'

export const login = async ({ email, password }) => {
    return await axios.post('/api/auth/login', { email, password })
}

export const refresh = async ({ refreshToken }) => {
    return await axios.post('/api/auth/refresh', { refreshToken })
}

export const activate = async ({ session, key }) => {
    console.log({ session, key })
    return await backend.patch(`/users/${session.sub}/activation`, { activationToken: key })
}
