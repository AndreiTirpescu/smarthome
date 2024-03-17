import React from 'react'

export default function MySmartHomeLogo ({ className }) {
    return (
        <div className={`w-10 h-10 shadow-xl bg-gradient-to-r from-accent to-accent-active rounded-xl flex justify-center items-center ${className}`}>
            <span className={'text-start text-xs font-bold text-white select-none'}>SH</span>
        </div>
    )
}
