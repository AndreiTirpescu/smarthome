import React from 'react'

const ClickableIcon = ({ icon, onClick = () => {} }) => {
    return (
        <div className={'rounded-full p-1 hover:bg-alto cursor-pointer'} onClick={onClick}>
            {icon}
        </div>
    )
}

export default ClickableIcon
