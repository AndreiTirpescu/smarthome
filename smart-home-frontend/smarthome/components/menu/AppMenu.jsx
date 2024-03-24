import React from 'react'
import List from '@/smarthome/components/simple/List'
import MenuItemComponent from '@/smarthome/components/menu/MenuItemComponent'
import { adminItems, allUsers, logout } from '@/smarthome/components/menu-items'
import MySmartHomeLogo from '@/smarthome/components/layout/MySmartHomeLogo'
import { useSession } from '@/smarthome/features/session/hooks'

export default function AppMenu () {
    const session = useSession()

    return (
        <div className={'flex-none h-screen bg-menuBackground hidden md:w-12 md:block lg:w-48'}>
            <div className={'flex flex-col h-full w-full justify-between py-4'}>
                <div className={'flex flex-col w-full gap-6'}>
                    <div className={'flex flex-col items-center'}>
                        <MySmartHomeLogo />
                    </div>

                    <List propName={'menuItem'} items={session?.role === 'ADMIN' ? [...allUsers, ...adminItems] : allUsers} itemComponent={MenuItemComponent} />
                </div>

                <List propName={'menuItem'} items={[logout]} itemComponent={MenuItemComponent} />
            </div>
        </div>
    )
}
