import React from 'react'
import UnauthenticatedPageLayout from '@/smarthome/features/session/components/UnauthenticatedPageLayout'
import UnauthenticatedForm from '@/smarthome/features/session/components/UnauthenticatedForm'
import SignUpInputs from '@/smarthome/features/session/components/SignUpInputs'

export default function LoginPage () {
    return (
        <UnauthenticatedPageLayout>
            <UnauthenticatedForm>
                <SignUpInputs />
            </UnauthenticatedForm>
        </UnauthenticatedPageLayout>
    )
}
