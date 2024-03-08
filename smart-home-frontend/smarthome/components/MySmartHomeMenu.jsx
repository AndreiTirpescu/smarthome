import React from 'react'
import List from '@/smarthome/components/List'
import MenuItemComponent from '@/smarthome/components/MenuItemComponent'
import menuItems from '@/smarthome/components/menu-items'
import MySmartHomeLogo from '@/smarthome/components/MySmartHomeLogo'

export default function MySmartHomeMenu () {
    return (
        <div className={'sticky top-0 h-full md:flex md:flex-col hidden md:w-20 lg:w-56 p-4 sm:gap-6 border-r'}>
            <MySmartHomeLogo className={'hidden lg:block'} />
            <List propName={'menuItem'} items={menuItems} itemComponent={MenuItemComponent} />
        </div>
    )
}
