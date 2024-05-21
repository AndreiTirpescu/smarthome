import React from 'react'
import { Cpu, HardDrive, HelpCircle, Home, LogOut, Settings } from 'react-feather'

export const menuItemsTop = [
    {
        key: 'menu_home',
        link: '/',
        name: 'Dashboard',
        icon: <Home />
    },
    {
        key: 'menu_system_management',
        link: '/homesystem-management',
        name: 'System Management',
        icon: <HardDrive />
    },
    {
        key: 'Device Catalog',
        link: '/devices',
        name: 'Device Catalog',
        icon: <Cpu />
    },
    {
        key: 'menu_settings',
        link: '/settings',
        name: 'Settings and account',
        icon: <Settings />
    }
]

export const bottomItems = [
    {
        key: 'menu_help',
        link: '/logout',
        name: 'Help and support',
        icon: <HelpCircle />
    },
    {
        key: 'menu_logout',
        link: '/logout',
        name: 'Logout',
        icon: <LogOut />
    }
]
