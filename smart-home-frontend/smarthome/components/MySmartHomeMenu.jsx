import React from 'react'
import List from '@/smarthome/components/List'
import MenuItemComponent from '@/smarthome/components/MenuItemComponent'
import menuItems from '@/smarthome/components/menu-items'

export default function MySmartHomeMenu ({ isExpanded = true }) {
    return (
        <div className={`h-full border-r hidden sm:w-auto ${isExpanded && 'lg:w-56'} p-4 sm:flex sm:flex-col sm:gap-6`}>
            <List propName={'menuItem'} items={menuItems} props={{ expanded: isExpanded }} itemComponent={MenuItemComponent} />
        </div>
    )
}
