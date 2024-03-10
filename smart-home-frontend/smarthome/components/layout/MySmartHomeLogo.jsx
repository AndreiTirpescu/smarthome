import React from 'react'

export default function MySmartHomeLogo ({ className }) {
    return (
        <span className={`text-start text-xl select-none ${className ?? ''}`}>my<span className={'font-bold text-accent'}>SmartHome</span></span>
    )
}
