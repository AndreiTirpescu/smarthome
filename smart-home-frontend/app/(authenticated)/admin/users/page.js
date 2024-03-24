'use client'

import React from 'react'
import { useFetchUsers } from '@/smarthome/features/users/hooks/useFetchUsers'
import UsersTable from '@/smarthome/features/users/components/UsersTable'
import Card from '@/smarthome/components/simple/Card'

const AdminUserManagementPage = () => {
    const { isLoading, users } = useFetchUsers(0, 100)

    return (
        <div className={ 'flex flex-col w-full gap-4' }>
            <Card className={'overflow-x-auto'}>
                <UsersTable users={ users } isLoading={ isLoading }/>
            </Card>
        </div>
    )
}

export default AdminUserManagementPage
