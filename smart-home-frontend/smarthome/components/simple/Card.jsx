import React from 'react'

const Card = ({ children, className }) => {
    return (
        <div className={`w-full bg-white ring-1 ring-accent/10 rounded-lg shadow-sm p-4 ${className}`}>
            {children}
        </div>
    )
}

export default Card
