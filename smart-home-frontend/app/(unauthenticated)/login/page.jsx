import React from 'react'
import UnauthenticatedForm from '@/smarthome/features/session/components/UnauthenticatedForm'
import LoginInputs from '@/smarthome/features/session/components/LoginInputs'

export default function LoginPage () {
    return (
        <UnauthenticatedForm>
            <LoginInputs/>
        </UnauthenticatedForm>
    )
}
