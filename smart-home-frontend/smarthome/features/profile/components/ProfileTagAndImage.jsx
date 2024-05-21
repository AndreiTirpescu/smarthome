'use client'
import React from 'react'
import { useFetchProfileInfo } from '@/smarthome/features/profile/hooks/useFetchProfileInfo'
import { Calendar } from 'react-feather'

const ProfileTagAndImage = () => {
    const { profileInfo, isLoading } = useFetchProfileInfo()
    const currentDate = () => {
        return new Date().toDateString()
    }
    return (
        <>
            {!isLoading && <div className={'flex justify-center select-none gap-14'}>
                <div className={'flex'}>
                    <div className={'flex items-center'}>
                        <Calendar className={'w-14 h-14 text-slate-400'} />
                        <p className={'text-base font-medium'}>{currentDate()}</p>
                    </div>
                </div>

                <div className={'flex flex-col items-center'}>
                    <div className={'relative rounded-full w-14 h-14'}>
                        <img className={'absolute w-full h-full rounded-full shadow-md'} src={`${profileInfo.profileImageUrl ?? '/default_avatar.jpg'}`} alt={'Profile Avatar'}/>
                    </div>
                    <p className={'text-base font-medium text-slate-400'}>{`@${profileInfo.tag}`}</p>
                </div>
            </div> }
        </>
    )
}

export default ProfileTagAndImage
