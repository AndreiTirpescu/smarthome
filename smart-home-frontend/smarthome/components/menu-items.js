import { HomeIcon, PowerIcon, UserGroupIcon } from '@heroicons/react/24/outline'
import React from 'react'
import { cookies } from 'next/headers'
import { jwtDecode } from 'jwt-decode'

const adminItems = [
    {
        key: 'menu_admin_users',
        link: '/admin/users',
        name: 'Users',
        icon: <UserGroupIcon />
    },
    {
        key: 'menu_admin_dev',
        link: '/admin/devices',
        name: 'Configuration'
    }
]

const allUsers = [
    {
        key: 'menu_home',
        link: '/',
        name: 'Home',
        icon: <HomeIcon />
    },
    {
        key: 'menu_logout',
        link: '/logout',
        name: 'Logout',
        icon: <PowerIcon />
    }
]

const userBasedMenuItems = () => {
    const accessToken = cookies().get('accessToken')?.value

    return jwtDecode(accessToken).role === 'ADMIN' ? [...allUsers, ...adminItems] : [...allUsers]
}

const menuItems = userBasedMenuItems()

export default menuItems
