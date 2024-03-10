'use client'
import React, { useEffect } from 'react'
import { deleteCookie } from 'cookies-next'
import { useRouter } from 'next/navigation'

export default function LogoutPage () {
    const router = useRouter()

    useEffect(() => {
        deleteCookie('accessToken')
        deleteCookie('refreshToken')
        router.push('/login')
    }, [])

    return (
        <>
        </>
    )
}
