import UnauthenticatedForm from '@/smarthome/features/session/components/UnauthenticatedForm'
import UnauthenticatedPageLayout from '@/smarthome/features/session/components/UnauthenticatedPageLayout'
import React from 'react'
import ForgotPasswordInputs from '@/smarthome/features/session/components/ForgotPasswordInputs'

export default function ForgotPasswordPage () {
    return (
        <UnauthenticatedPageLayout>
            <UnauthenticatedForm>
                <ForgotPasswordInputs />
            </UnauthenticatedForm>
        </UnauthenticatedPageLayout>
    )
}
