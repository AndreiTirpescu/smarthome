import React from 'react'
import AppTableHeader from '@/smarthome/components/table/AppTableHeader'
import AppTableRows from '@/smarthome/components/table/AppTableRows'
import Card from '@/smarthome/components/simple/Card'

const AppTable = ({ items, cellConfig, headerConfig }) => {
    return (
        <div className={'flex flex-col py-4'}>
            <table className={'w-full whitespace-nowrap border-separate border-spacing-y-3 cursor-default p-4'}>
                <AppTableHeader headers={headerConfig} />
                <AppTableRows items={items} cellConfig={cellConfig} headers={headerConfig} />
            </table>
        </div>
    )
}

export default AppTable
