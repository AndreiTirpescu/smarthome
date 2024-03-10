import UserStatusBadge from '@/smarthome/features/users/components/UserStatusBadge'
import UserRoleBadge from '@/smarthome/features/users/components/UserRoleBadge'
import UserActions from '@/smarthome/features/users/components/UserActions'

const cells = [
    {
        header: 'email',
        keys: ['email']
    },
    {
        header: 'role',
        keys: ['role'],
        component: UserRoleBadge
    },
    {
        header: 'status',
        keys: ['status'],
        component: UserStatusBadge
    },
    {
        header: 'actions',
        keys: ['id'],
        component: UserActions
    }
]

const headers = [
    {
        name: 'email'
    },
    {
        name: 'role'
    },
    {
        name: 'status'
    },
    {
        name: 'actions'
    }
]

const userTableConfig = { cells, headers }

export default userTableConfig
