import React from 'react'
import List from '@/smarthome/components/simple/List'
import MenuItemComponent from '@/smarthome/components/menu/MenuItemComponent'
import { menuItemsTop, bottomItems } from '@/smarthome/components/menu-items-top'

export default function AppMenu () {
    return (
        <div className={'flex-none h-screen border-r border-r-slate-300'}>
            <div className={'flex flex-col h-full w-full justify-between p-4'}>
                <div className={'flex flex-col w-full gap-8'}>
                    <span className={'text-2xl text-start px-4 w-full select-none'}>My Smart Home</span>

                    <List className={'gap-8'} propName={'menuItem'} items={menuItemsTop} itemComponent={MenuItemComponent} />
                </div>

                <div className={'flex flex-col gap-8'}>
                    <div className={'relative h-[200px] bg-red-200 rounded-md shadow-md'}>
                        <img src={'/menu_hero.jpg'}
                            className={'w-full h-full absolute z-0 object-cover object-center rounded-md select-none'}
                            alt={'Banner Image'}/>
                        <div className={ 'rounded-md absolute z-5 bg-gradient-to-t from-black to-transparent w-full h-full opacity-65' } />
                        <div className={'absolute z-10 w-full h-full flex flex-col-reverse justify-start'}>
                            <span className={'text-base font-medium text-white p-4 select-none'}>Browse the Device Catalog and enhance your smart home experience</span>
                        </div>
                    </div>
                    <List className={'gap-8'} propName={'menuItem'} items={bottomItems} itemComponent={MenuItemComponent} />
                </div>
            </div>
        </div>
    )
}
