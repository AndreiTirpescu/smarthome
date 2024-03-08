import React from 'react'
import ClickableIcon from '@/smarthome/components/ClickableIcon'
import { Bell, Menu } from 'react-feather'
import MySmartHomeLogo from '@/smarthome/components/MySmartHomeLogo'
const ToolBoxBar = ({ onMenuClicked }) => {
    return (
        <header className={'h-14 w-full border-b flex justify-between items-center'}>
            <div className={'flex h-full items-center gap-6'}>
                <ClickableIcon className={'md:hidden'} icon={ <Menu /> } onClick={onMenuClicked}/>
                <MySmartHomeLogo className={'lg:hidden'} />
            </div>

            <div className={'flex gap-2 h-full items-center'}>
                <ClickableIcon icon={ <Bell/> }/>
            </div>
        </header>

    )
}

export default ToolBoxBar
