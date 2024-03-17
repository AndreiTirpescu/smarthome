import React from 'react'
import ClickableIcon from '@/smarthome/components/simple/ClickableIcon'
import { Bell, Menu } from 'react-feather'

const ToolBoxBar = ({ onMenuClicked }) => {
    return (
        <header className={'h-14 w-full absolute flex justify-between bg-white px-4 md:px-14'}>
            <div className={'flex h-full items-center gap-6'}>
                <ClickableIcon className={'md:hidden'} icon={Menu} onClick={onMenuClicked}/>
            </div>

            <div className={'flex gap-2 h-full items-center'}>
                <ClickableIcon icon={ Bell }/>
            </div>
        </header>

    )
}

export default ToolBoxBar
