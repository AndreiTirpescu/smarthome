'use client'

import React, { useState } from 'react'
import MySmartHomeMenu from '@/smarthome/components/menu/MySmartHomeMenu'
import AppDrawer from '@/smarthome/components/menu/AppDrawer'
import ToolBoxBar from '@/smarthome/components/menu/ToolBoxBar'

export default function PageLayout ({ children }) {
    const [drawerOpened, setDrawerOpened] = useState(false)

    return (
        <>
            <div className="flex bg-pageBackground">
                <MySmartHomeMenu/>

                <main className={'flex flex-1 w-full h-screen relative overflow-y-auto '}>
                    <ToolBoxBar onMenuClicked={() => setDrawerOpened(!drawerOpened)} />

                    <div className={'mt-14 mb-14 w-full px-4 md:px-14 py-6'}>
                        {children}
                        <div className={'h-6'}></div>
                    </div>
                </main>

            </div>
            {
                drawerOpened && <AppDrawer onRequestClose={() => setDrawerOpened(false)} />
            }
        </>
    )
}
