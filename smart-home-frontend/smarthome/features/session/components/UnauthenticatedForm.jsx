import React from 'react'
import MySmartHomeLogo from '@/smarthome/components/layout/MySmartHomeLogo'

export default function UnauthenticatedForm ({ children }) {
    return (
        <form className={'flex flex-col justify-between w-full h-full px-16 py-32 bg-white'}>
            <span className={'text-3xl'}>My smart home</span>
            {children}
        </form>
    )
}
