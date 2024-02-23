'use client'

import React, { useState } from 'react'
import Input from '@/smarthome/components/Input'
import Button from '@/smarthome/components/Button'
import Link from 'next/link'
import translations from '@/smarthome/lib/errorkeys'
import { useRouter } from 'next/navigation'
import { login } from '@/smarthome/features/session/api'

export default function LoginInputs () {
    const [loginInput, setLoginInput] = useState({ email: '', password: '' })
    const [error, setError] = useState('')
    const [loading, setLoading] = useState(false)
    const router = useRouter()

    const onSubmit = async (e) => {
        e.preventDefault()
        if (loading) {
            return
        }
        try {
            setLoading(true)
            await login({ ...loginInput })
            setLoading(false)
            router.push('')
        } catch (e) {
            setError(translations[e.response.data.key])
        }
    }

    const onDataChange = (e) => {
        const { name, value } = e.target
        setLoginInput({ ...loginInput, [name]: value })
    }

    return (
        <>
            <h3 className={'text-md text-start text-secondary select-none'}>Sign in to your smart home account</h3>
            <Input name={'email'} type={'email'} label={'email'} value={loginInput.email} onChange={onDataChange}/>
            <Input name={'password'} type={'password'} label={'password'} value={loginInput.password} onChange={onDataChange} />
            <Button label={'Continue'} onClick={onSubmit} loading={loading} />
            {error && <p className={'text-xs text-red-400 font-semibold text-center'}>{error}</p>}
            <div className={'flex flex-col gap-2'}>
                <Link href={'/signup'} className={'text-accent-hover text-sm text-center font-semibold hover:text-accent-active cursor-pointer'}>Don&apos;t have an account? Sign up</Link>
                <Link href={'/forgotpassword'} className={'text-accent-hover text-xs text-center font-semibold hover:text-accent-active cursor-pointer'}>Forgot Password</Link>
            </div>
        </>
    )
}
