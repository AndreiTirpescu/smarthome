'use client'

import React from 'react'
import Link from 'next/link'
import { usePathname } from 'next/navigation'

export default function MenuItemComponent ({ menuItem, isDrawer = false }) {
    const pathName = usePathname()
    const isActive = pathName === menuItem.link

    return (
        <li className={'px-4'}>
            <Link href={ menuItem.link } className={'w-full flex gap-4 items-center group'}>
                <span className={`${isActive && 'text-accent'} group-hover:text-accent-hover`}>{menuItem.icon}</span>

                <span className={`text-base ${isActive && 'text-accent'} group-hover:text-accent-hover`}>{ menuItem.name }</span>
            </Link>
        </li>

    )
}
