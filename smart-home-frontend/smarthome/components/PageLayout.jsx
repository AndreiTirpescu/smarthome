'use client'

import React, { useState } from 'react'
import MySmartHomeMenu from '@/smarthome/components/MySmartHomeMenu'
import AppDrawer from '@/smarthome/components/AppDrawer'
import ToolBoxBar from '@/smarthome/components/ToolBoxBar'

export default function PageLayout ({ children }) {
    const [expandMenu, setExpandMenu] = useState(false)

    return (
        <main className="h-screen flex flex-col bg-primary">
            <ToolBoxBar onMenuClicked={() => setExpandMenu(!expandMenu)} />

            <div className={'w-full h-full flex flex-row'}>
                <MySmartHomeMenu isExpanded={expandMenu} />

                <div className={'flex flex-1 h-full grow overflow-y-auto p-4'}>
                    {children}
                </div>
            </div>

            {
                expandMenu && <AppDrawer onRequestClose={() => setExpandMenu(false)} />
            }
        </main>
    )
}
