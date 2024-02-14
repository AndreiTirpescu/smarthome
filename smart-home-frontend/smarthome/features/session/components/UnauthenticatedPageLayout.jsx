import React from 'react'

export default function UnauthenticatedPageLayout ({ children }) {
    return (
        <main className={'w-full min-h-screen bg-white flex justify-center items-center bg-cover'}
            style={{ backgroundImage: 'url(\'/background.svg\')' }}>
            { children }
        </main>
    )
}
