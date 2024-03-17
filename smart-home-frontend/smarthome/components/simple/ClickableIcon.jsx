import React from 'react'

const ClickableIcon = ({ icon: Icon, className, onClick = () => {} }) => {
    return (
        <div className={`rounded-full p-2 group hover:ring-1 hover:ring-accent/10 hover:bg-accent/10 cursor-pointer ${className ?? ''}`} onClick={onClick}>
            <Icon className={'w-4 h-4 group-hover:shadow-xl group-hover:text-accent group-hover:fill-accent'} />
        </div>
    )
}

export default ClickableIcon
