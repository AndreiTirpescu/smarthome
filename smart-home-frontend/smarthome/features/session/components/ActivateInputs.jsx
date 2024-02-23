'use client'

import React, { useState } from 'react'
import Input from '@/smarthome/components/Input'
import Button from '@/smarthome/components/Button'
import { backend } from '@/smarthome/lib'
import translations from '@/smarthome/lib/errorkeys'
import { useRouter } from 'next/navigation'
import { getCookie } from 'cookies-next'
import { jwtDecode } from 'jwt-decode'
import axios from 'axios'

export default function LoginInputs ({ onSignupClicked }) {
    const [activationInput, setActivationInput] = useState({ key: '' })
    const [error, setError] = useState('')
    const router = useRouter()

    const onSubmit = async (e) => {
        e.preventDefault()
        try {
            const accessToken = getCookie('accessToken')
            const refreshToken = getCookie('accessToken')
            await backend.patch(`/users/${jwtDecode(accessToken).sub}/activation`, { activationToken: activationInput.key })
            await axios.post('/api/auth/refresh', { refreshToken })
            router.push('/login')
        } catch (e) {
            setError(translations[e.response.data.key])
        }
    }

    const onDataChange = (e) => {
        const { name, value } = e.target
        setActivationInput({ ...activationInput, [name]: value.toUpperCase() })
    }

    return (
        <>
            <h3 className={'text-md text-start text-secondary select-none'}>Let&apos;s activate your account</h3>
            <Input name={'key'} type={'text'} label={'Activation Key'} value={activationInput.key} onChange={onDataChange}/>
            <Button label={'Activate account'} onClick={onSubmit} />
            <p className={'text-xs text-red-400 font-semibold text-center'}>{error}</p>
        </>
    )
}
