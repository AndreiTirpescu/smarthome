import React from 'react'

export default function Button ({ label, onClick, disabled = false }) {
    return (
        <button className={'bg-accent rounded-md px-2 py-3 hover:bg-accent-hover border border-alto'} onClick={onClick}>
            <span className={'font-bold text-white'}>{label}</span>
        </button>
    )
}
