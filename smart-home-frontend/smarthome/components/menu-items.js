import React from 'react'
import { Cpu, Home, LogOut, Users } from 'react-feather'

export const adminItems = [
    {
        key: 'menu_admin_users',
        link: '/admin/users',
        name: 'Users',
        icon: <Users />
    },
    {
        key: 'menu_admin_dev',
        link: '/admin/devices',
        name: 'Devices',
        icon: <Cpu />
    }

]

export const allUsers = [
    {
        key: 'menu_home',
        link: '/',
        name: 'Home',
        icon: <Home />
    }
]

export const logout = {
    key: 'menu_logout',
    link: '/logout',
    name: 'Logout',
    icon: <LogOut />
}
