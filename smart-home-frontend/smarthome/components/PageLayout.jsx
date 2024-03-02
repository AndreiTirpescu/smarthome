'use client'

import React, { useState } from 'react'
import MySmartHomeMenu from '@/smarthome/components/MySmartHomeMenu'
import { Bell, Menu } from 'react-feather'
import MySmartHomeLogo from '@/smarthome/components/MySmartHomeLogo'
import ClickableIcon from '@/smarthome/components/ClickableIcon'
import AppDrawer from '@/smarthome/components/AppDrawer'

export default function PageLayout ({ children }) {
    const [showDrawer, setShowDrawer] = useState(false)

    return (
        <main className="h-screen flex flex-col bg-primary">
            <div className={'h-14 w-full border-b flex flex-row justify-between'}>
                <div className={'flex flex-row h-full items-center align-center gap-6 pl-5'}>
                    <ClickableIcon icon={ <Menu /> } onClick={() => setShowDrawer(!showDrawer)}/>
                    <MySmartHomeLogo />
                </div>

                <div className={'flex flex-row gap-2 h-full items-center pr-5'}>
                    <ClickableIcon icon={ <Bell/> }/>
                </div>
            </div>

            <div className={'w-full h-full flex flex-row'}>
                <MySmartHomeMenu isExpanded={showDrawer} />

                <div className={'flex flex-1 h-full grow overflow-y-auto p-4'}>
                    {children}
                </div>
            </div>

            <AppDrawer shouldShow={showDrawer} onRequestClose={() => setShowDrawer(false)} />
        </main>
    )
}
