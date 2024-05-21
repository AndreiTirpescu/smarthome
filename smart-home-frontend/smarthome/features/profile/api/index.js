import { backend } from '@/smarthome/lib'

export const fetchProfileInfo = async ({ identityId }) => {
    return await backend.get(`/profiles/identity/${identityId}`)
}
