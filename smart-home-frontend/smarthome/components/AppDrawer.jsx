'use client'
import React from 'react'
import ClickableIcon from '@/smarthome/components/ClickableIcon'
import { X } from 'react-feather'
import MySmartHomeLogo from '@/smarthome/components/MySmartHomeLogo'
import List from '@/smarthome/components/List'
import menuItems from '@/smarthome/components/menu-items'
import { DrawerItemComponent } from '@/smarthome/components/MenuItemComponent'

const AppDrawer = ({ onRequestClose }) => {
    return (
        <div className={'absolute top-0 z-20 h-full w-full bg-black bg-opacity-50 backdrop-blur-sm md:hidden' }>
            <div className={'w-3/4 h-full flex flex-col gap-0'}>
                <div className={ 'flex flex-row h-14 w-3/4 bg-white items-center align-center gap-6 pl-5 transition ease-in-out delay-150' }>
                    <ClickableIcon icon={ <X/> } onClick={onRequestClose}/>
                    <MySmartHomeLogo/>
                </div>
                <div className={ 'w-3/4 p-4 flex flex-col gap-6 bg-white h-full' }>
                    <List propName={ 'menuItem' } items={ menuItems } itemComponent={ DrawerItemComponent }/>
                </div>
            </div>
        </div>
    )
}

export default AppDrawer
