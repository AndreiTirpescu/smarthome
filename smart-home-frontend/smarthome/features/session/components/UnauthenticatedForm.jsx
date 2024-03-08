import React from 'react'
import MySmartHomeLogo from '@/smarthome/components/layout/MySmartHomeLogo'

export default function UnauthenticatedForm ({ children }) {
    return (
        <form className={'flex flex-col gap-8 py-12 px-10 w-full sm:w-[514px] bg-white sm:rounded-md sm:shadow-xl border border-alto'}>
            <MySmartHomeLogo />
            {children}
        </form>
    )
}
