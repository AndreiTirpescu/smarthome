'use client'

import React, { useState } from 'react'
import Input from '@/smarthome/components/Input'
import Button from '@/smarthome/components/Button'
import Link from 'next/link'
import { backend } from '@/smarthome/lib'
import translations from '@/smarthome/lib/errorkeys'
import { useRouter } from 'next/navigation'
import { getCookie } from 'cookies-next'
import { jwtDecode } from 'jwt-decode'

export default function LoginInputs ({ onSignupClicked }) {
    const [activationInput, setActivationInput] = useState({ key: '' })
    const [error, setError] = useState('')
    const router = useRouter()

    const onSubmit = async (e) => {
        e.preventDefault()
        try {
            const accessToken = getCookie('accessToken')
            await backend.patch(`/users/${jwtDecode(accessToken).sub}/activation`, { activationToken: activationInput.key })
            router.push('')
        } catch (e) {
            console.log(e)
            setError(translations[e.response.data.key])
        }
    }

    const onDataChange = (e) => {
        const { name, value } = e.target
        setActivationInput({ ...activationInput, [name]: value })
    }

    return (
        <>
            <h3 className={'text-md text-start text-secondary select-none'}>Let&apos;s activate your account</h3>
            <Input name={'key'} type={'text'} label={'Activation Key'} value={activationInput.key} onChange={onDataChange}/>
            <Button label={'Activate account'} onClick={onSubmit} />
            <p className={'text-xs text-red-400 font-semibold text-center'}>{error}</p>
            <div className={'flex flex-col gap-2'}>
                <Link href={'/signup'} className={'text-accent-hover text-sm text-center font-semibold hover:text-accent-active cursor-pointer'}>Don&apos;t have an account? Sign up</Link>
                <Link href={'/login'} className={'text-accent-hover text-xs text-center font-semibold hover:text-accent-active cursor-pointer'}>Back to Log in</Link>
            </div>
        </>
    )
}
