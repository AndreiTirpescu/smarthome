'use client'

import React, { useState } from 'react'
import MySmartHomeMenu from '@/smarthome/components/MySmartHomeMenu'
import AppDrawer from '@/smarthome/components/AppDrawer'
import ToolBoxBar from '@/smarthome/components/ToolBoxBar'

export default function PageLayout ({ children }) {
    const [drawerOpened, setDrawerOpened] = useState(false)

    return (
        <>
            <div className="h-screen w-screen flex">
                <MySmartHomeMenu/>

                <main className={'flex flex-col w-full h-screen overflow-y-auto px-4 md:px-14'}>
                    <ToolBoxBar onMenuClicked={() => setDrawerOpened(!drawerOpened)} />

                    <div className={'w-full bg-green-200'}>
                        {children}
                    </div>
                </main>

            </div>
            {
                drawerOpened && <AppDrawer onRequestClose={() => setDrawerOpened(false)} />
            }
        </>
    )
}
