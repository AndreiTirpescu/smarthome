'use client'

import React, { useState } from 'react'
import MySmartHomeMenu from '@/smarthome/components/menu/MySmartHomeMenu'
import AppDrawer from '@/smarthome/components/menu/AppDrawer'
import ToolBoxBar from '@/smarthome/components/menu/ToolBoxBar'

export default function PageLayout ({ children }) {
    const [drawerOpened, setDrawerOpened] = useState(false)

    return (
        <>
            <div className="h-screen w-screen flex bg-primary">
                <MySmartHomeMenu/>

                <main className={'flex flex-col w-full h-screen overflow-y-auto '}>
                    <ToolBoxBar onMenuClicked={() => setDrawerOpened(!drawerOpened)} />

                    <div className={' w-full px-4 md:px-14 py-6'}>
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
