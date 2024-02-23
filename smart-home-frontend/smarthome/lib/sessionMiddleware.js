import Axios from 'axios'
import { serialize } from 'cookie'
import { jwtDecode } from 'jwt-decode'

const axios = Axios.create({
    baseURL: 'http://localhost:8080/api/v1/auth',
    headers: {
        Accept: 'application/json',
        'Content-Type': 'application/json'
    }
})

const maxAgeFrom = (token) => {
    const expiryDate = jwtDecode(token).exp

    return expiryDate - Math.floor(Date.now() / 1000)
}

export const serializedTokens = ({ accessToken, refreshToken }) => {
    const serializedAccessToken = serialize('accessToken', accessToken, {
        httpOnly: false,
        sameSite: 'strict',
        maxAge: maxAgeFrom(accessToken),
        path: '/'
    })

    const serializedRefreshToken = serialize('refreshToken', refreshToken, {
        httpOnly: false,
        sameSite: 'strict',
        maxAge: maxAgeFrom(refreshToken),
        path: '/'
    })

    return [serializedAccessToken, serializedRefreshToken]
}

export default axios
