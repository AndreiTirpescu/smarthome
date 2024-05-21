'use client'

import React, { useState } from 'react'
import AppMenu from '@/smarthome/components/menu/AppMenu'
import AppDrawer from '@/smarthome/components/menu/AppDrawer'

export default function PageLayout ({ children }) {
    const [drawerOpened, setDrawerOpened] = useState(false)

    return (
        <>
            <div className="flex bg-white">
                <div className={'hidden md:block'}>
                    <AppMenu/>
                </div>

                <main className={'flex flex-1 w-full item h-screen relative xl:justify-center overflow-y-auto'}>
                    <div className={'flex-1 w-full xl:max-w-[1280px] p-8'}>
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
