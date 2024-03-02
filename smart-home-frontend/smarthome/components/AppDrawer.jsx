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
        <>
            <div className={'w-screen h-screen bg-black bg-opacity-50 absolute z-10 md:hidden' }>
                <div className={ 'flex flex-row h-14 w-3/4 bg-white items-center align-center gap-6 pl-5 transition ease-in-out delay-150' }>
                    <ClickableIcon icon={ <X/> } onClick={onRequestClose}/>
                    <MySmartHomeLogo/>
                </div>
                <div className={ 'w-3/4 p-4 flex flex-col gap-6 bg-white h-full' }>
                    <List propName={ 'menuItem' } items={ menuItems } itemComponent={ DrawerItemComponent }/>
                </div>
            </div>
        </>
    )
}

export default AppDrawer
