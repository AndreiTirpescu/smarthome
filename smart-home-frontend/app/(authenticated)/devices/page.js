'use client'
import React from 'react'
import ProfileTagAndImage from '@/smarthome/features/profile/components/ProfileTagAndImage'
import CatalogCard from '@/smarthome/features/devices/components/CatalogCard'
import { useFetchDevices } from '@/smarthome/features/devices/hooks/useFetchDevices'
import DeviceCard from '@/smarthome/features/devices/components/DeviceCard'

export default function DeviceCatalog () {
    const { devices, isLoading } = useFetchDevices(0, 100)

    return (
        <div className={'w-full flex flex-col gap-12'}>
            <div className={'w-full flex justify-between items-center'}>
                <CatalogCard text={'Your Device Hub'} />
                <ProfileTagAndImage />
            </div>

            <div className={'w-full grid grid-cols-12 gap-8'}>
                {!isLoading && devices.map(device => {
                    return <div key={device.id} className={'col-span-3'}>
                        <DeviceCard device={device} />
                    </div>
                })}
            </div>
        </div>
    )
}
