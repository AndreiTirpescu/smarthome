'use client'
import React from 'react'
import { useSession } from '@/smarthome/features/session/hooks'
import AppMenu from '@/smarthome/components/menu/AppMenu'

const AppDrawer = ({ onRequestClose }) => {
    const session = useSession()

    return (
        <div className={'absolute top-0 z-20 h-screen w-full bg-black bg-opacity-50 backdrop-blur-sm md:hidden'}>
            <div className={'w-3/4 h-full flex flex-col gap-0'}>
                <AppMenu />
            </div>
        </div>
    )
}

export default AppDrawer
