import React from 'react'
import { Codesandbox, MapPin } from 'react-feather'

const DeviceListItemView = ({ device }) => {
    return (
        <div className={'w-full h-18 rounded-md flex gap-6 border-alto border items-center bg-white py-2 group hover:bg-accent-active hover:text-white'}>
            <div className={'border-r border-r-alto w-32 px-4 flex flex-col items-center justify-center'}>
                <p className={'text-sm cursor-default font-bold text-accent group-hover:text-white'}>{device.name}</p>
            </div>
            <div className={'flex flex-col gap-4'}>
                <div className={'flex gap-2 items-center'}>
                    <Codesandbox className={'text-alto-dark group-hover:text-white w-3 h-3 text-sm'} />
                    <p className={'text-xs group-hover:text-white cursor-default'}>{device.name}</p>
                </div>
                <div className={'flex gap-2 items-center'}>
                    <MapPin className={'text-alto-dark group-hover:text-white w-3 h-3 text-sm'} />
                    <p className={'text-xs group-hover:text-white cursor-default'}>{device.typeCode}</p>
                </div>
            </div>
        </div>
    )
}

export default DeviceListItemView
