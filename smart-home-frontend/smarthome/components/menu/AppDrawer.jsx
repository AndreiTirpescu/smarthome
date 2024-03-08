'use client'
import React from 'react'
import ClickableIcon from '@/smarthome/components/simple/ClickableIcon'
import { X } from 'react-feather'
import MySmartHomeLogo from '@/smarthome/components/layout/MySmartHomeLogo'
import List from '@/smarthome/components/simple/List'
import { adminItems, allUsers, logout } from '@/smarthome/components/menu-items'
import { DrawerItemComponent } from '@/smarthome/components/menu/MenuItemComponent'
import { useSession } from '@/smarthome/features/session/hooks'

const AppDrawer = ({ onRequestClose }) => {
    const session = useSession()

    return (
        <div className={'absolute top-0 z-20 h-full w-full bg-black bg-opacity-50 backdrop-blur-sm md:hidden'}>
            <div className={'w-1/2 h-full flex flex-col gap-0'}>
                <div className={'w-full px-4 flex flex-col gap-2 bg-white h-full'}>
                    <div className={'flex flex-row h-14 w-full bg-white items-center align-center gap-6 transition ease-in-out delay-150'}>
                        <ClickableIcon icon={ <X/> } onClick={onRequestClose}/>
                        <MySmartHomeLogo/>
                    </div>

                    <List propName={'menuItem'} items={allUsers} itemComponent={DrawerItemComponent} />
                    {
                        session?.role === 'ADMIN' && (
                            <List propName={'menuItem'} items={adminItems} itemComponent={DrawerItemComponent} />
                        )
                    }
                    <List propName={'menuItem'} items={[logout]} itemComponent={DrawerItemComponent} />
                </div>
            </div>
        </div>
    )
}

export default AppDrawer
