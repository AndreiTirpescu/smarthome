import React from 'react'
import ProfileTagAndImage from '@/smarthome/features/profile/components/ProfileTagAndImage'

import DeviceViewTopCard from '@/smarthome/features/devices/components/DeviceViewTopCard'
import DeviceOverview from '@/smarthome/features/devices/components/DeviceOverview'
import DeviceBreakdown from '@/smarthome/features/devices/components/DeviceBreakdown'

export default function DeviceCatalog () {
    // const { devices, isLoading } = useFetchDevices(0, 100)

    return (
        <div className={'w-full flex flex-col gap-12'}>
            <div className={'w-full flex justify-between items-center'}>
                <DeviceViewTopCard />
                <ProfileTagAndImage />
            </div>

            <DeviceOverview />
            <DeviceBreakdown />
        </div>
    )
}
