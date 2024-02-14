import React from 'react'

export default function Input ({ label, type, onChange, error, value, name = '' }) {
    return (
        <label className={'flex flex-col gap-2 w-full'}>
            <p className={'text-sm capitalize text-secondary hover:font-bold font-semibold'}>{ label }</p>
            <input name={name} type={type} className={`text-md rounded-md border ${error ? 'border-red-600' : ' border-alto'} px-2 py-3`} onChange={onChange} value={value} />
            { { error } && <p className={'text-xs text-red-600'}>{ error }</p> }
        </label>
    )
}
