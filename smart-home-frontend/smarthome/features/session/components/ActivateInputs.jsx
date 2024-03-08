'use client'

import React, { useState } from 'react'
import Input from '@/smarthome/components/simple/Input'
import Button from '@/smarthome/components/simple/Button'
import translations from '@/smarthome/lib/errorkeys'
import { useRouter } from 'next/navigation'
import { useSession } from '@/smarthome/features/session/hooks/useSession'
import { useRefreshSession } from '@/smarthome/features/session/hooks/useRefreshSession'
import { activate } from '@/smarthome/features/session/api'

export default function LoginInputs () {
    const [activationInput, setActivationInput] = useState({ key: '' })
    const [error, setError] = useState('')
    const router = useRouter()

    const session = useSession()
    const refreshSession = useRefreshSession()

    const onSubmit = async (e) => {
        e.preventDefault()
        try {
            await activate({ session, ...activationInput })
            await refreshSession()
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
