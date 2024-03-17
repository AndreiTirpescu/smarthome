'use client'

import React from 'react'
import Link from 'next/link'
import { usePathname } from 'next/navigation'

export default function MenuItemComponent ({ menuItem, isDrawer = false }) {
    const pathName = usePathname()
    const isActive = pathName === menuItem.link

    return (
        <li className={'px-2'}>
            <Link href={ menuItem.link } className={`w-full px-2 py-3 gap-2 flex items-center rounded-lg justify-start group hover:ring-1 hover:shadow-md hover:ring-accent/10 ${isActive && 'ring-1 shadow-md ring-accent/30'}`}>
                <span className={`${isActive && 'text-accent'} group-hover:text-accent`}>{menuItem.icon}</span>

                <span className={`text-xs ${isDrawer ? 'block' : 'hidden lg:block'} ${isActive && 'font-semibold text-accent'} group-hover:text-accent`}>{ menuItem.name }</span>
            </Link>
        </li>

    )
}

const withDrawerComponent = MenuItemComponent => {
    const DrawerItemComponent = (props) => {
        const { menuItem } = { ...props }
        return <MenuItemComponent isDrawer={true} menuItem={menuItem} />
    }
    DrawerItemComponent.displayName = 'DrawerItemComponent'

    return DrawerItemComponent
}

export const DrawerItemComponent = withDrawerComponent(MenuItemComponent)
