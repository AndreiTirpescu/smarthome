'use client'

import React, { useState } from 'react'
import AppMenu from '@/smarthome/components/menu/AppMenu'
import AppDrawer from '@/smarthome/components/menu/AppDrawer'

export default function PageLayout ({ children }) {
    const [drawerOpened, setDrawerOpened] = useState(false)

    return (
        <>
            <div className="flex bg-white">
                <AppMenu/>

                <main className={'flex flex-1 w-full h-screen relative overflow-y-auto'}>
                    <div className={'mt-14 flex-1 w-full px-4 lg:px-14 py-6'}>
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
