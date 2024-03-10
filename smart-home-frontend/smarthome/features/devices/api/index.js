import { backend } from '@/smarthome/lib'

export const getDevicesPaged = async ({ pageNumber = 0, pageSize = 100 }) => {
    return await backend.get('/devices', { params: { pageNumber, pageSize } })
}
