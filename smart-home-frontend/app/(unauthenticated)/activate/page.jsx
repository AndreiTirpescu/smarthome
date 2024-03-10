import React from 'react'
import UnauthenticatedForm from '@/smarthome/features/session/components/UnauthenticatedForm'
import ActivateInputs from '@/smarthome/features/session/components/ActivateInputs'

export default function LoginPage () {
    return (
        <UnauthenticatedForm>
            <ActivateInputs/>
        </UnauthenticatedForm>
    )
}
