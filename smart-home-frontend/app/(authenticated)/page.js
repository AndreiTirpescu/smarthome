import React from 'react'
import WelcomeCard from '@/smarthome/features/profile/components/WelcomeCard'
import ProfileTagAndImage from '@/smarthome/features/profile/components/ProfileTagAndImage'
import LatestConnectedDeviceCardList from '@/smarthome/features/homesystem/components/LatestConnectedDeviceCardList'

export default function Dashboard () {
    return (
        <div className={'w-full flex flex-col gap-12'}>
            <div className={'w-full flex justify-between items-center'}>
                <WelcomeCard />
                <ProfileTagAndImage />
            </div>

            <div className={'w-full flex flex-col gap-4'}>
                <p className={'text-base font-semibold'}>Recently Added – Your Newest Device Connections</p>
                <LatestConnectedDeviceCardList />
            </div>
        </div>
    )
}
