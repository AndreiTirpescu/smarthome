'use client'

import React, { useState } from 'react'
import MySmartHomeMenu from '@/smarthome/components/MySmartHomeMenu'
import AppDrawer from '@/smarthome/components/AppDrawer'
import ToolBoxBar from '@/smarthome/components/ToolBoxBar'

export default function PageLayout ({ children }) {
    const [expandMenu, setExpandMenu] = useState(false)

    return (
        <>
            <div className="h-screen w-screen">
                <ToolBoxBar onMenuClicked={() => setExpandMenu(!expandMenu)} />

                <main className={'flex h-[calc(100vh-3.5rem)] overflow-y-auto'}>
                    <MySmartHomeMenu isExpanded={expandMenu} />

                    <div className={'w-full px-14 py-6 bg-green-200'}>
                        {children}
                    </div>
                </main>

            </div>
            {
                expandMenu && <AppDrawer onRequestClose={() => setExpandMenu(false)} />
            }
        </>
    )
}
