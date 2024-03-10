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
        <div className={'absolute top-0 z-20 h-screen w-full bg-black bg-opacity-50 backdrop-blur-sm md:hidden'}>
            <div className={'w-3/4 h-full flex flex-col gap-0'}>
                <div className={'w-full px-4 flex flex-col bg-white h-full justify-between'}>
                    <div className={'flex flex-col gap-2'}>
                        <div className={'flex flex-row h-14 w-full bg-white items-center align-center gap-6 transition ease-in-out delay-150 border-alto border-b'}>
                            <ClickableIcon icon={ <X/> } onClick={onRequestClose}/>
                            <MySmartHomeLogo/>
                        </div>

                        <List propName={'menuItem'} items={session?.role === 'ADMIN' ? [...allUsers, ...adminItems] : allUsers} itemComponent={DrawerItemComponent} />
                    </div>

                    <div className={'flex flex-col py-4'}>
                        <List propName={'menuItem'} items={[logout]} itemComponent={DrawerItemComponent} />
                    </div>
                </div>
            </div>
        </div>
    )
}

export default AppDrawer
