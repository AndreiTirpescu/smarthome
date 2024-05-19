'use client'

import React, { useState } from 'react'
import Input from '@/smarthome/components/simple/Input'
import Button from '@/smarthome/components/simple/Button'
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
            <div className={'w-full flex flex-col gap-12'}>
                <h3 className={'text-md text-start text-secondary select-none font-medium text-2xl'}>Log in</h3>
                <div className={'w-full flex flex-col gap-4'}>
                    <Input name={'email'} type={'email'} label={'email'} value={loginInput.email} onChange={onDataChange}/>
                    <Input name={'password'} type={'password'} label={'password'} value={loginInput.password} onChange={onDataChange} />
                </div>
                <Button label={'Login'} onClick={onSubmit} loading={loading} />
            </div>
            {error && <p className={'text-xs text-red-400 font-semibold text-center'}>{error}</p>}
            <div className={'flex flex-col gap-1'}>
                <Link href={'/signup'} className={'text-center font-medium'}>Don&apos;t have an account? <span className={'underline text-accent hover:text-accent-active'}>Sign up</span></Link>
                <Link href={'/forgotpassword'} className={'text-accent-hover text-xs text-center font-semibold hover:text-accent-active underline cursor-pointer'}>Forgot Password Link</Link>
            </div>
        </>
    )
}
