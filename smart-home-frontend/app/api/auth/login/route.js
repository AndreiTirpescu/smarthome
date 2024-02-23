import axios, { serializedTokens } from '@/smarthome/lib/sessionMiddleware'

export async function POST (request) {
    const { email, password } = await request.json()

    try {
        const authResp = await axios.post('login', { email, password })

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
