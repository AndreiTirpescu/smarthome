import React from 'react'

const UserStatusBadge = ({ status }) => {
    return (
        <span className={`px-4 py-2 rounded-2xl capitalize text-sm shadow-sm ${status === 'ACTIVE' && 'bg-green-300'} 
        ${status === 'INACTIVE' && 'bg-red-300'} 
        ${status === 'PENDING_ACTIVATION' && 'bg-yellow-300'}`}>
            {status.replace('_', ' ').toLowerCase()}
        </span>
    )
}

export default UserStatusBadge
