import React from 'react'
import { Star, User } from 'react-feather'

const UserRoleBadge = ({ role }) => {
    return (
        <div className={'flex items-center justify-start gap-2 text-left rounded-2xl capitalize text-sm w-auto'}>
            {role === 'ADMIN' && <Star className={'w-4 h-4 text-accent fill-accent'} />}
            {role === 'STANDARD' && <User className={'w-4 h-4 text-accent fill-accent'} />}
        </div>
    )
}

export default UserRoleBadge
