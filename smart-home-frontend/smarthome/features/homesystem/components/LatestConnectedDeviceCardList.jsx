'use client'
import React from 'react'
import { useFetchLatestConnected } from '@/smarthome/features/homesystem/hooks/useFetchLatestConnected'
import LatestConnectedDeviceCard from '@/smarthome/features/homesystem/components/LatestConnectedDeviceCard'

const LatestConnectedDeviceCardList = () => {
    const { connectedDevices, loadingConnectedDevices } = useFetchLatestConnected(0, 4)
    return (
        <div className={'w-full grid grid-cols-4 gap-8'}>
            {!loadingConnectedDevices && connectedDevices.map((device, idx) =>
                <div key={idx} className={'col-span-2'}>
                    <LatestConnectedDeviceCard connectedDevice={device} />
                </div>
            )}
        </div>
    )
}

export default LatestConnectedDeviceCardList
