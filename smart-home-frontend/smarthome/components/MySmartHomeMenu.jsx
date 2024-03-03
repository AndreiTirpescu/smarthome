import React from 'react'
import List from '@/smarthome/components/List'
import MenuItemComponent from '@/smarthome/components/MenuItemComponent'
import menuItems from '@/smarthome/components/menu-items'

export default function MySmartHomeMenu ({ isExpanded = true }) {
    return (
        <div className={`sticky top-0 h-full sm:flex sm:flex-col hidden sm:w-20 ${isExpanded && 'lg:w-56'} p-4 sm:gap-6 border-r`}>
            <List propName={'menuItem'} items={menuItems} props={{ expanded: isExpanded }} itemComponent={MenuItemComponent} />
        </div>
    )
}
