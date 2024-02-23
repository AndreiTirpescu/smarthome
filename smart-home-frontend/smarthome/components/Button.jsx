import React from 'react'

export default function Button ({ label, onClick, disabled = false, loading = false }) {
    return (
        <button className={'bg-accent rounded-md px-2 py-3 hover:bg-accent-hover border border-alto flex items-center justify-center'} onClick={onClick}>
            {!loading
                ? <span className={'font-bold text-white text-center h-7'}>{label}</span>
                : <img alt={ 'loading' } src={ '/spinner.svg' } className={ 'w-7 h-7 fill-white text-white animate-spin' }/>}
        </button>
    )
}
