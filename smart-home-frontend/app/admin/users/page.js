'use client'

import React, { useEffect } from 'react'
import PageLayout from '@/smarthome/components/layout/PageLayout'
import { useFetchUsersPaged } from '@/smarthome/features/users/hooks/useFetchUsersPaged'
import AppTable from '@/smarthome/components/table/AppTable'

const AdminUserManagementPage = () => {
    const { isLoading, users } = useFetchUsersPaged(0, 100)

    const headers = [
        {
            name: 'email'
        },
        {
            name: 'role'
        },
        {
            name: 'status'
        }
    ]

    const CustomComp = ({ email, role }) => {
        return (<p>{email}</p>)
    }

    const cells = [
        {
            header: 'email',
            keys: ['email', 'role'],
            component: CustomComp
        },
        {
            header: 'role',
            keys: ['role']
        },
        {
            header: 'status',
            keys: ['status']
        }
    ]

    return (
        <PageLayout>
            <div className={'flex flex-col w-full gap-4 overflow-x-auto bg-white border border-alto rounded-md'}>
                <AppTable items={users} cellConfig={[...cells]} headerConfig={headers} />
            </div>
        </PageLayout>
    )
}

export default AdminUserManagementPage
