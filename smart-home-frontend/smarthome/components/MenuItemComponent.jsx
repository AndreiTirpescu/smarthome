'use client'

import React from 'react'
import Link from 'next/link'
import { usePathname } from 'next/navigation'

export default function MenuItemComponent ({ menuItem, expanded = true }) {
    const pathName = usePathname()
    const isActive = pathName === menuItem.link

    return (
        <li>
            <Link href={ menuItem.link } className={`flex gap-6 p-2 align-center items-center hover:bg-alto hover:rounded-md 
            ${isActive && 'bg-alto rounded-md'}`}>
                <div className={`w-6 h-6 ${!expanded && 'm-auto'} ${isActive && 'fill-black text-center ce'}`}>
                    {menuItem.icon}
                </div>

                {expanded && <span className={'text-sm'}>{ menuItem.name }</span>}
            </Link>
        </li>

    )
}

const withDrawerComponent = MenuItemComponent => {
    const DrawerItemComponent = (props) => {
        const { menuItem } = { ...props }
        return <MenuItemComponent expanded={true} menuItem={menuItem} />
    }
    DrawerItemComponent.displayName = 'DrawerItemComponent'

    return DrawerItemComponent
}

export const DrawerItemComponent = withDrawerComponent(MenuItemComponent)
