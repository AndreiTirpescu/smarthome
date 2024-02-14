import Axios from 'axios'

const axiosConfig = (config) => {
    config.headers.Accept = 'application/json'

    return config
}

export const backend = Axios.create({
    baseURL: 'http://localhost:8080/api/v1'
})

backend.interceptors.request.use(axiosConfig)

export default backend
