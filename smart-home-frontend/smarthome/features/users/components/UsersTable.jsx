import React from 'react'
import AppTable from '@/smarthome/components/table/AppTable'
import userTableConfig from '@/smarthome/features/users/components/userTableConfiguration'

const UsersTable = ({ users, isLoading }) => {
    const { cells, headers } = userTableConfig

    return (
        <AppTable items={users} cellConfig={[...cells]} headerConfig={headers} />
    )
}

export default UsersTable
