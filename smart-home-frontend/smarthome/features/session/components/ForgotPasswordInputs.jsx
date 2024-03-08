'use client'

import React, { useState } from 'react'
import Input from '@/smarthome/components/simple/Input'
import Button from '@/smarthome/components/simple/Button'
import Link from 'next/link'

export default function ForgotPasswordInputs () {
    const [forgotPasswordInput, setForgotPasswordInput] = useState({ email: '' })

    const onSubmit = (e) => {
        e.preventDefault()
    }

    const onDataChange = (e) => {
        const { name, value } = e.target
        setForgotPasswordInput({ ...forgotPasswordInput, [name]: value })
    }

    return (
        <>
            <h3 className={'text-md text-start text-secondary select-none'}>Enter your email and we&apos;ll send you a recovery link</h3>
            <Input name={'email'} type={'email'} label={'email'} value={forgotPasswordInput.email} onChange={onDataChange}/>
            <Button label={'Send link'} onClick={onSubmit} />
            <Link href={'/login'} className={'text-accent-hover text-sm text-center font-semibold hover:text-accent-active cursor-pointer'}>Back to sign in</Link>
        </>
    )
}
