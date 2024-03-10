'use client'

import React from 'react'
import PageLayout from '@/smarthome/components/layout/PageLayout'
import { useFetchUsersPaged } from '@/smarthome/features/users/hooks/useFetchUsersPaged'

const AdminUserManagementPage = () => {
    const { isLoading, users } = useFetchUsersPaged(0, 100)

    return (
        <PageLayout>
            <div className={'flex flex-col w-full gap-4 overflow-x-auto bg-white border border-alto rounded-md'}>
                <div className={'w-full flex flex-col py-4'}>
                    <div className={'w-full flex justify-between p-4'}>

                    </div>
                    <table className={'w-full whitespace-nowrap border-separate border-spacing-y-3  p-4'}>
                        <thead className={'table-auto bg-accent text-primary'}>
                            <tr className={'capitalize shadow-sm'}>
                                <th className={'text-start border-t border-l border-b border-alto rounded-l-md p-4'}>email</th>
                                <th className={'text-start border-t border-b border-alto p-4'}>status</th>
                                <th className={'text-start border-t border-r border-b border-alto rounded-r-md p-4'}>role</th>
                            </tr>
                        </thead>
                        <tbody className={'table-auto text-sm'}>
                            {users.map(u => (
                                <tr key={u.id} className={'shadow-sm'}>
                                    <td className={'text-start border-t border-l border-b border-alto rounded-l-md p-4'}>{u.email}</td>
                                    <td className={'text-start border-t border-b border-alto p-4'}>{u.status}</td>
                                    <td className={'text-start border-t border-r border-b border-alto rounded-r-md p-4'}>{u.role}</td>
                                </tr>
                            ))}
                            {users.map(u => (
                                <tr key={u.id} className={'shadow-sm'}>
                                    <td className={'text-start border-t border-l border-b border-alto rounded-l-md p-4'}>{u.email}</td>
                                    <td className={'text-start border-t border-b border-alto p-4'}>{u.status}</td>
                                    <td className={'text-start border-t border-r border-b border-alto rounded-r-md p-4'}>{u.role}</td>
                                </tr>
                            ))}{users.map(u => (
                                <tr key={u.id} className={'shadow-sm'}>
                                    <td className={'text-start border-t border-l border-b border-alto rounded-l-md p-4'}>{u.email}</td>
                                    <td className={'text-start border-t border-b border-alto p-4'}>{u.status}</td>
                                    <td className={'text-start border-t border-r border-b border-alto rounded-r-md p-4'}>{u.role}</td>
                                </tr>
                            ))}{users.map(u => (
                                <tr key={u.id} className={'shadow-sm'}>
                                    <td className={'text-start border-t border-l border-b border-alto rounded-l-md p-4'}>{u.email}</td>
                                    <td className={'text-start border-t border-b border-alto p-4'}>{u.status}</td>
                                    <td className={'text-start border-t border-r border-b border-alto rounded-r-md p-4'}>{u.role}</td>
                                </tr>
                            ))}{users.map(u => (
                                <tr key={u.id} className={'shadow-sm'}>
                                    <td className={'text-start border-t border-l border-b border-alto rounded-l-md p-4'}>{u.email}</td>
                                    <td className={'text-start border-t border-b border-alto p-4'}>{u.status}</td>
                                    <td className={'text-start border-t border-r border-b border-alto rounded-r-md p-4'}>{u.role}</td>
                                </tr>
                            ))}{users.map(u => (
                                <tr key={u.id} className={'shadow-sm'}>
                                    <td className={'text-start border-t border-l border-b border-alto rounded-l-md p-4'}>{u.email}</td>
                                    <td className={'text-start border-t border-b border-alto p-4'}>{u.status}</td>
                                    <td className={'text-start border-t border-r border-b border-alto rounded-r-md p-4'}>{u.role}</td>
                                </tr>
                            ))}{users.map(u => (
                                <tr key={u.id} className={'shadow-sm'}>
                                    <td className={'text-start border-t border-l border-b border-alto rounded-l-md p-4'}>{u.email}</td>
                                    <td className={'text-start border-t border-b border-alto p-4'}>{u.status}</td>
                                    <td className={'text-start border-t border-r border-b border-alto rounded-r-md p-4'}>{u.role}</td>
                                </tr>
                            ))}{users.map(u => (
                                <tr key={u.id} className={'shadow-sm'}>
                                    <td className={'text-start border-t border-l border-b border-alto rounded-l-md p-4'}>{u.email}</td>
                                    <td className={'text-start border-t border-b border-alto p-4'}>{u.status}</td>
                                    <td className={'text-start border-t border-r border-b border-alto rounded-r-md p-4'}>{u.role}</td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                </div>
            </div>
        </PageLayout>
    )
}

export default AdminUserManagementPage
