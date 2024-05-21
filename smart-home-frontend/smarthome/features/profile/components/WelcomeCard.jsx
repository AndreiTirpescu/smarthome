'use client'
import React from 'react'
import { useFetchProfileInfo } from '@/smarthome/features/profile/hooks/useFetchProfileInfo'

const WelcomeCard = () => {
    const { profileInfo, isLoading } = useFetchProfileInfo()

    return (
        <>
            {!isLoading && <div className={'flex flex-col'}>
                <p className={'text-base font-semibold'}>{`Welcome, ${profileInfo.personalDetails.firstName} ${profileInfo.personalDetails.lastName}`}</p>
                <p className={'text-base font-semibold text-gray-400'}>{'Check your latest activity'}</p>
            </div> }
        </>
    )
}

export default WelcomeCard
