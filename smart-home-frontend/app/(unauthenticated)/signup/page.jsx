import React from 'react'
import UnauthenticatedForm from '@/smarthome/features/session/components/UnauthenticatedForm'
import SignUpInputs from '@/smarthome/features/session/components/SignUpInputs'

export default function SignUpPage () {
    return (
        <UnauthenticatedForm>
            <SignUpInputs/>
        </UnauthenticatedForm>
    )
}
