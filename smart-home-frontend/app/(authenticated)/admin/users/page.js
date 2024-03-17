'use client'

import React from 'react'
import { useFetchUsers } from '@/smarthome/features/users/hooks/useFetchUsers'
import UsersTable from '@/smarthome/features/users/components/UsersTable'

const AdminUserManagementPage = () => {
    const { isLoading, users } = useFetchUsers(0, 100)

    return (
        <div className={ 'flex flex-col w-full gap-4' }>
            <UsersTable users={ users } isLoading={ isLoading }/>
        </div>
    )
}

export default AdminUserManagementPage
