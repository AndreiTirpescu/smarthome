import React, { useState } from 'react'
import ClickableIcon from '@/smarthome/components/simple/ClickableIcon'
import { List } from 'react-feather'
import { useClickAway } from '@uidotdev/usehooks'

const UserActions = ({ id }) => {
    const [actionsOpened, setActionsOpened] = useState(false)
    const ref = useClickAway(() => {
        setActionsOpened(false)
    })

    return (
        <div className={'flex w-full relative'}>
            <ClickableIcon icon={<List className={'text-gray-500 w-5 h-5'}/>} onClick={() => { setActionsOpened(!actionsOpened) }} />
            {
                actionsOpened && <div className={'absolute right-[80%] z-10 mt-2 w-56 origin-top-right top-5 rounded-md border border-alto bg-white'} ref={ref}>
                    <span href="#" className="block px-4 py-2 text-sm text-gray-700 cursor-pointer hover:bg-alto" role="menuitem" tabIndex="-1"
                        id="menu-item-0">Deactivate user</span>
                    <span href="#" className="block px-4 py-2 text-sm text-gray-700 cursor-pointer hover:bg-alto" role="menuitem" tabIndex="-1"
                        id="menu-item-1">Change Role</span>
                </div>
            }
        </div>
    )
}

export default UserActions
