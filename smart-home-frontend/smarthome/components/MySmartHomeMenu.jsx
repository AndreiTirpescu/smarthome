import React from 'react'
import MySmartHomeLogo from '@/smarthome/components/MySmartHomeLogo'
import List from '@/smarthome/components/List'
import MenuItemComponent from '@/smarthome/components/MenuItemComponent'
import menuItems from '@/smarthome/components/menu-items'

export default function MySmartHomeMenu () {
    return (
        <div className={'h-screen w-64 p-8 bg-alto-light flex flex-col gap-6'}>
            <MySmartHomeLogo />
            <List propName={'menuItem'} items={menuItems} itemComponent={MenuItemComponent} />
        </div>
    )
}
