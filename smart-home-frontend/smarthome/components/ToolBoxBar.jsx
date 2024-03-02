import React from 'react'
import ClickableIcon from '@/smarthome/components/ClickableIcon'
import { Bell, Menu } from 'react-feather'
import MySmartHomeLogo from '@/smarthome/components/MySmartHomeLogo'
const ToolBoxBar = ({ onMenuClicked }) => {
    return (
        <div className={'h-14 w-full border-b flex flex-row justify-between'}>
            <div className={'flex flex-row h-full items-center align-center gap-6 pl-5'}>
                <ClickableIcon icon={ <Menu /> } onClick={onMenuClicked}/>
                <MySmartHomeLogo />
            </div>

            <div className={'flex flex-row gap-2 h-full items-center pr-5'}>
                <ClickableIcon icon={ <Bell/> }/>
            </div>
        </div>

    )
}

export default ToolBoxBar
