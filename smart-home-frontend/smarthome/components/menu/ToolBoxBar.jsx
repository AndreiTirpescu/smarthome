import React from 'react'
import ClickableIcon from '@/smarthome/components/simple/ClickableIcon'
import { Bell, Menu } from 'react-feather'
import MySmartHomeLogo from '@/smarthome/components/layout/MySmartHomeLogo'
const ToolBoxBar = ({ onMenuClicked }) => {
    return (
        <header className={'min-h-14 w-full border-b flex justify-between items-center bg-white px-4 md:px-14'}>
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
