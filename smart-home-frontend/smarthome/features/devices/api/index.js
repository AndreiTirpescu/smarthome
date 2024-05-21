import { backend } from '@/smarthome/lib'

export const getDevicesPaged = async ({ pageNumber = 0, pageSize = 100 }) => {
    return await backend.get('/devices', { params: { pageNumber, pageSize } })
}

export const getDeviceEvents = async (deviceId) => {
    return await backend.get(`/devices/${deviceId}/events`)
}

export const getDeviceValues = async (deviceId) => {
    return await backend.get(`/devices/${deviceId}/values`)
}

export const getDeviceByTypeCode = async (typeCode) => {
    return await backend.get(`/devices/typecode/${typeCode}`)
}

export const getDeviceById = async (id) => {
    return await backend.get(`/devices/${id}`)
}
