import { backend } from '@/smarthome/lib'

export const getAllUsers = async ({ pageNumber = 0, pageSize = 100 }) => {
    return await backend.get('/users', { params: { pageNumber, pageSize } })
}
