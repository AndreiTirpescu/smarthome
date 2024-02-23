import axios from 'axios'
import { jwtDecode } from 'jwt-decode'
import { serialize } from 'cookie'

const maxAgeFrom = (token) => {
    const expiryDate = jwtDecode(token).exp

    return expiryDate - Math.floor(Date.now() / 1000)
}

export async function POST (request) {
    const { refreshToken: refreshTkn } = await request.json()

    try {
        const authResp = await axios
            .post('http://localhost:8080/api/v1/auth/token/refresh',
                { refreshToken: refreshTkn },
                { headers: { 'Content-Type': 'application/json' } }
            )

        const { accessToken, refreshToken } = authResp.data

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

        return new Response(JSON.stringify(authResp.data), {
            status: authResp.data.status,
            headers: {
                'Set-Cookie': [serializedAccessToken, serializedRefreshToken]
            }
        })
    } catch (e) {
        return new Response(JSON.stringify(e.response.data), {
            status: e.response.status
        })
    }
}
