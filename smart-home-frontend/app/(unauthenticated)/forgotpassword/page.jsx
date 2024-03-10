import UnauthenticatedForm from '@/smarthome/features/session/components/UnauthenticatedForm'
import React from 'react'
import ForgotPasswordInputs from '@/smarthome/features/session/components/ForgotPasswordInputs'

export default function ForgotPasswordPage () {
    return (
        <UnauthenticatedForm>
            <ForgotPasswordInputs />
        </UnauthenticatedForm>
    )
}
