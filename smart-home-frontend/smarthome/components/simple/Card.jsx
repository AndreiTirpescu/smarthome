import React from 'react'

const Card = ({ children, className }) => {
    return (
        <div className={`w-full ${className}`}>
            {children}
        </div>
    )
}

export default Card
