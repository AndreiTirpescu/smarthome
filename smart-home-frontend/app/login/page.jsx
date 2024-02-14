import React from 'react'
import UnauthenticatedPageLayout from '@/smarthome/features/session/components/UnauthenticatedPageLayout'
import UnauthenticatedForm from '@/smarthome/features/session/components/UnauthenticatedForm'
import LoginInputs from '@/smarthome/features/session/components/LoginInputs'

export default function LoginPage () {
    return (
        <UnauthenticatedPageLayout>
            <UnauthenticatedForm>
                <LoginInputs />
            </UnauthenticatedForm>
        </UnauthenticatedPageLayout>
    )
}
