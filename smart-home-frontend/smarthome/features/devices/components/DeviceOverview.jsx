'use client'
import React from 'react'
import { useFetchDeviceByTypeCode } from '@/smarthome/features/devices/hooks/useFetchDeviceByTypeCode'
import { usePathname } from 'next/navigation'

const DeviceOverview = () => {
    const pathname = usePathname()
    const { device, isLoading } = useFetchDeviceByTypeCode({ typeCode: pathname.replace('/devices/', '') })

    return (
        <>
            {!isLoading && <div className={'flex w-full justify-between'}>
                <div className={'w-96 h-80'}>
                    <img src={`${device.imageUrl}`} className={'w-full h-full object-fit object-contain rounded-md'} alt={'Device Image'} />
                </div>
                <div className={'flex flex-col gap-6 p-6 max-w-96'}>
                    <p className={'font-semibold text-5xl'}>{`${device.name}`}</p>
                    <p className={'font-medium text-base'}>{`${device.shortDescription}`}</p>
                </div>
            </div>}
        </>
    )
}

export default DeviceOverview
