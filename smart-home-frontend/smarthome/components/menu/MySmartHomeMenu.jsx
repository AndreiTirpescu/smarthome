import React from 'react'
import List from '@/smarthome/components/simple/List'
import MenuItemComponent from '@/smarthome/components/menu/MenuItemComponent'
import { adminItems, allUsers, logout } from '@/smarthome/components/menu-items'
import MySmartHomeLogo from '@/smarthome/components/layout/MySmartHomeLogo'
import { useSession } from '@/smarthome/features/session/hooks'

export default function MySmartHomeMenu () {
    const session = useSession()

    return (
        <div className={'sticky top-0 h-full md:flex md:flex-col hidden md:w-20 lg:w-56 p-4 justify-between sm:gap-6 border-r bg-white'}>
            <div className={'flex flex-col gap-6'}>
                <MySmartHomeLogo className={'hidden lg:block'} />

                <List propName={'menuItem'} items={session?.role === 'ADMIN' ? [...allUsers, ...adminItems] : allUsers} itemComponent={MenuItemComponent} />
            </div>

            <List propName={'menuItem'} items={[logout]} itemComponent={MenuItemComponent} />
        </div>
    )
}
