import React from 'react'
import AppTableHeader from '@/smarthome/components/table/AppTableHeader'
import AppTableRows from '@/smarthome/components/table/AppTableRows'

const AppTable = ({ items, cellConfig, headerConfig }) => {
    return (
        <div className={'flex flex-col'}>
            <table className={'w-full'}>
                <AppTableHeader headers={headerConfig} />
                <AppTableRows items={items} cellConfig={cellConfig} headers={headerConfig} />
            </table>
        </div>
    )
}

export default AppTable
