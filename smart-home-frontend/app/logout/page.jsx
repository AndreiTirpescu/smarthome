'use client'
import React, { useEffect } from 'react'
import UnauthenticatedPageLayout from '@/smarthome/features/session/components/UnauthenticatedPageLayout'
import { deleteCookie } from 'cookies-next'
import { useRouter } from 'next/navigation'

export default function LoginPage () {
    const router = useRouter()

    useEffect(() => {
        deleteCookie('accessToken')
        deleteCookie('refreshToken')
        router.push('/login')
    }, [])

    return (
        <UnauthenticatedPageLayout/>
    )
}
