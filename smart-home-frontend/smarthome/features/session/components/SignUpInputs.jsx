'use client'

import React, { useState } from 'react'
import Input from '@/smarthome/components/Input'
import Button from '@/smarthome/components/Button'
import Link from 'next/link'

export default function SignUpInputs () {
    const [signUpInput, setSignUpInput] = useState({ email: '', password: '', confirmedPassword: '' })

    const onSubmit = (e) => {
        e.preventDefault()
    }

    const onDataChanged = (e) => {
        const { name, value } = e.target
        setSignUpInput({ ...signUpInput, [name]: value })
    }

    return (
        <>
            <h3 className={'text-md text-start text-secondary select-none'}>Sign in to your smart home account</h3>
            <Input name={'email'} type={'email'} label={'email'} value={signUpInput.email} onChange={onDataChanged}/>
            <Input name={'password'} type={'password'} label={'password'} value={signUpInput.password} onChange={onDataChanged} />
            <Input name={'confirm password'} type={'password'} label={'Confirm password'} value={signUpInput.confirmedPassword} onChange={onDataChanged} />
            <Button label={'Continue'} onClick={onSubmit} />

            <Link href={'/login'} className={'text-accent-hover text-sm text-center font-semibold hover:text-accent-active cursor-pointer'}>Back to sign in</Link>
        </>
    )
}
