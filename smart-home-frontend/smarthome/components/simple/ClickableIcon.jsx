import React from 'react'

const ClickableIcon = ({ icon, className, onClick = () => {} }) => {
    return (
        <div className={`rounded-full p-1 hover:bg-alto cursor-pointer ${className ?? ''}`} onClick={onClick}>
            {icon}
        </div>
    )
}

export default ClickableIcon
