import Axios from 'axios'
import { getCookie } from 'cookies-next'

const axiosConfig = (config) => {
    const token = getCookie('accessToken')
    config.headers.Accept = 'application/json'

    if (token) {
        config.headers.Authorization = `Bearer ${token}`
    }

    return config
}

export const backend = Axios.create({
    baseURL: process.env.BACKEND_BASE_URL_API_V1
})

backend.interceptors.request.use(axiosConfig)

export default backend
