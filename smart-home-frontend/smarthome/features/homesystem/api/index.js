import { backend } from '@/smarthome/lib'

export const getLatestConnectedDevices = async (homeSystemId, pageNumber = 0, pageSize = 4) => {
    return await backend.get(`/homesystems/${homeSystemId}/devices`, { params: { pageNumber, pageSize } })
}

export const getHomeSystemByIdentityId = async (identityId) => {
    return await backend.get(`/homesystems/identity/${identityId}`)
}
