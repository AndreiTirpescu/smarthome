import axios from 'axios'
import { jwtDecode } from 'jwt-decode'
import { serialize } from 'cookie'

const maxAgeFrom = (token) => {
    const expiryDate = jwtDecode(token).exp

    return expiryDate - Math.floor(Date.now() / 1000)
}

export async function POST (request) {
    const { email, password } = await request.json()

    try {
        const authResp = await axios
            .post('http://localhost:8080/api/v1/auth/login',
                { email, password },
                { headers: { 'Content-Type': 'application/json' } }
            )

        const { accessToken } = authResp.data
        // const { expDateStr, maxAge } = dateFromJWT(accessToken)
        // const { expDateStrRefresh, maxAgeRefresh } = dateFromJWT(accessToken)

        const serializedAccessToken = serialize('accessToken', accessToken, {
            httpOnly: false,
            sameSite: 'strict',
            maxAge: maxAgeFrom(accessToken),
            path: '/'
        })

        const serializedRefreshToken = serialize('refreshToken', accessToken, {
            httpOnly: false,
            sameSite: 'strict',
            maxAge: maxAgeFrom(accessToken),
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
