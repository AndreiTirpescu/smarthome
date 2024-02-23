import axios from 'axios'
import { serializedTokens } from '@/smarthome/lib/sessionMiddleware'

export async function POST (request) {
    const { refreshToken: refreshTkn } = await request.json()

    try {
        const authResp = await axios.post('/refresh', { refreshToken: refreshTkn })

        return new Response(JSON.stringify(authResp.data), {
            status: authResp.data.status,
            headers: {
                'Set-Cookie': serializedTokens({ ...authResp.data })
            }
        })
    } catch (e) {
        return new Response(JSON.stringify(e.response.data), {
            status: e.response.status
        })
    }
}
