'use client'

import React from 'react'
import PageLayout from '@/smarthome/components/layout/PageLayout'
import { useFetchUsersPaged } from '@/smarthome/features/users/hooks/useFetchUsersPaged'

const AdminUserManagementPage = () => {
    const { isLoading, users } = useFetchUsersPaged(0, 100)

    return (
        <PageLayout>
            <div className={'w-80 h-80'}>
                {isLoading && <p>Loading...</p>}
                {
                    users.map(u => (<p key={'123'}>{`${u.email} ${u.role} ${u.status}`}</p>))
                }
            </div>
        </PageLayout>
    )
}

export default AdminUserManagementPage
