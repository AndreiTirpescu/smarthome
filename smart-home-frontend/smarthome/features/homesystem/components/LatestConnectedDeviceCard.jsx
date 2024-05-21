'use client'
import React from 'react'
import { useFetchDeviceById } from '@/smarthome/features/devices/hooks/useFetchDeviceById'

const LatestConnectedDeviceCard = ({ connectedDevice }) => {
    const { device, loadingDeviceById } = useFetchDeviceById(connectedDevice.deviceCatalogId)

    return (
        <div className={'w-full flex gap-6 border border-slate-300 rounded-md'}>
            {!loadingDeviceById && (
                <div className={'w-24 h-24 flex'}>
                    <img src={`${device.imageUrl}`} className={'w-full h-full object-cover object-center rounded-md'} alt={'Device image'} />
                </div>
            )}

            <div className={'w-full py-4 flex flex-col'}>
                <p className={'text-base font-semibold'}>{connectedDevice.deviceName}</p>

                <p className={'text-base font-semibold text-slate-300'}>{`${new Date(connectedDevice.connectedAt).toDateString()}`}</p>
            </div>

        </div>
    )
}

export default LatestConnectedDeviceCard
