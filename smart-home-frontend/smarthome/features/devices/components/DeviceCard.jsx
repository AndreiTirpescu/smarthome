import React from 'react'
import Link from 'next/link'

const DeviceCard = ({ device }) => {
    return (
        <div className={'w-full flex flex-col'}>
            <div className={'w-full h-80 flex justify-between '}>
                <img src={`${device.imageUrl}`} className={'w-full h-full object-cover object-center rounded-md border border-slate-300'} alt={'Device image'} />
            </div>
            <div className={'w-full py-4 flex justify-between'}>
                <p className={'text-base font-medium'}>{device.name}</p>
                <p className={'text-base text-slate-400'}>{device.typeCode}</p>
            </div>
            <Link href={`/devices/${device.typeCode}`}>
                <p className={'text-base text-accent hover:text-accent-hover'}>Click to see more details</p>
            </Link>
        </div>
    )
}

export default DeviceCard
