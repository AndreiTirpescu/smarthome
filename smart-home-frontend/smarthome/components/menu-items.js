import React from 'react'
import { jwtDecode } from 'jwt-decode'
import { Cpu, Home, LogOut, Users } from 'react-feather'
import { getCookie } from 'cookies-next'

const adminItems = [
    {
        key: 'menu_home',
        link: '/',
        name: 'Home',
        icon: <Home />
    },
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
    },
    {
        key: 'menu_logout',
        link: '/logout',
        name: 'Logout',
        icon: <LogOut />
    }
]

const allUsers = [
    {
        key: 'menu_home',
        link: '/',
        name: 'Home',
        icon: <Home />
    },
    {
        key: 'menu_logout',
        link: '/logout',
        name: 'Logout',
        icon: <LogOut />
    }
]

const userBasedMenuItems = () => {
    const { role } = { ...jwtDecode(getCookie('accessToken')) }

    return role === 'ADMIN' ? adminItems : allUsers
}

const menuItems = userBasedMenuItems()

export default menuItems
