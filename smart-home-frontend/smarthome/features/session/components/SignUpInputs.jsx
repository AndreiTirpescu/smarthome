'use client'

import React, { useState } from 'react'
import Input from '@/smarthome/components/simple/Input'
import Button from '@/smarthome/components/simple/Button'
import Link from 'next/link'
import { signup } from '@/smarthome/features/session/api'
import { translations } from '@/smarthome/lib'
import { useRouter } from 'next/navigation'

export default function SignUpInputs () {
    const [signUpInput, setSignUpInput] = useState({ email: '', password: '', confirmedPassword: '' })
    const [loading, setLoading] = useState(false)
    const [error, setError] = useState(false)
    const router = useRouter()

    const onSubmit = async (e) => {
        e.preventDefault()
        if (loading) {
            return
        }
        setLoading(true)
        try {
            if (signUpInput.password !== signUpInput.confirmedPassword) {
                setError('Passwords must match')
                return
            }

            const { email, password } = signUpInput
            await signup({ email, password })
            router.push('/login')
        } catch (e) {
            setError(translations[e.response.data.key])
        }

        setLoading(false)
    }

    const onDataChanged = (e) => {
        const { name, value } = e.target
        setSignUpInput({ ...signUpInput, [name]: value })
    }

    return (
        <>
            <div className={'w-full flex flex-col gap-12'}>
                <h3 className={'text-md text-start text-secondary select-none'}>Let&apos;s make a new account</h3>
                <div className={'w-full flex flex-col gap-4'}>
                    <Input name={'email'} type={'email'} label={'email'} value={signUpInput.email} onChange={onDataChanged}/>
                    <Input name={'password'} type={'password'} label={'password'} value={signUpInput.password} onChange={onDataChanged} />
                    <Input name={'confirmedPassword'} type={'password'} label={'Confirm password'} value={signUpInput.confirmedPassword} onChange={onDataChanged} />
                </div>
            </div>
            {error && <p className={'text-xs text-red-400 font-semibold text-center'}>{error}</p>}
            <Button label={'Continue'} onClick={onSubmit} loading={loading} />

            <Link href={'/login'} className={'text-accent-hover underline text-sm text-center font-semibold hover:text-accent-active cursor-pointer'}>Back to sign in</Link>
        </>
    )
}
