'use client'
import React from 'react'
import { usePathname } from 'next/navigation'

const DeviceViewTopCard = () => {
    const pathname = usePathname()

    return (
        <>
            <div className={'flex flex-col'}>
                <p className={'text-base font-semibold'}>Device catalog</p>
                <p className={'text-base font-semibold text-gray-400'}>{pathname.replace('/devices/', '')}</p>
            </div>
        </>
    )
}

export default DeviceViewTopCard
