import React from 'react'
import UnauthenticatedPageLayout from '@/smarthome/features/session/components/UnauthenticatedPageLayout'
import UnauthenticatedForm from '@/smarthome/features/session/components/UnauthenticatedForm'
import ActivateInputs from '@/smarthome/features/session/components/ActivateInputs'

export default function LoginPage () {
    return (
        <UnauthenticatedPageLayout>
            <UnauthenticatedForm>
                <ActivateInputs />
            </UnauthenticatedForm>
        </UnauthenticatedPageLayout>
    )
}
