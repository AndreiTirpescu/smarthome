'use client'

import React from 'react'
import Link from 'next/link'
import { usePathname } from 'next/navigation'

export default function MenuItemComponent ({ menuItem }) {
    const pathName = usePathname()
    const isActive = pathName === menuItem.link

    return (
        <li>
            <Link href={ menuItem.link } className={`flex flex-row gap-1 items-center ${isActive && 'font-semibold bg-alto rounded-md'}`}>
                <div className={'w-8 h-8 p-2'}>
                    {menuItem.icon}
                </div>

                <span className={`text-md ${isActive && 'font-semibold'}`}>
                    { menuItem.name }
                </span>
            </Link>
        </li>

    )
}
