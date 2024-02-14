import React from 'react'
import MySmartHomeMenu from '@/smarthome/components/MySmartHomeMenu'

export default function PageLayout ({ children }) {
    return (
        <main className="min-h-screen flex flex-row gap-0 bg-primary">
            <MySmartHomeMenu />

            <div className={'flex flex-1 h-screen grow overflow-y-auto p-8'}>
                {children}
            </div>
        </main>
    )
}
