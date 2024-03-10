'use client'

import React from 'react'
import { useFetchUsersPaged } from '@/smarthome/features/users/hooks/useFetchUsersPaged'
import UsersTable from '@/smarthome/features/users/components/UsersTable'

const AdminUserManagementPage = () => {
    const { isLoading, users } = useFetchUsersPaged(0, 100)

    return (
        <div className={ 'flex flex-col w-full gap-4 overflow-x-auto bg-white border border-alto rounded-md' }>
            <UsersTable users={ users } isLoading={ isLoading }/>
        </div>
    )
}

export default AdminUserManagementPage
