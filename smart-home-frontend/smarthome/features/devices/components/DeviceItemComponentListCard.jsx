import React from 'react'
import { Codesandbox, MapPin } from 'react-feather'
import Card from '@/smarthome/components/simple/Card'

const DeviceListItemView = ({ device }) => {
    return (
        <Card className={'h-18 flex gap-6 items-center cursor-pointer group hover:bg-accent-active hover:text-white'}>
            <div className={'border-r border-r-slate-300 w-32 px-4 flex flex-col items-center justify-center'}>
                <p className={'text-sm font-bold text-accent group-hover:text-white'}>{device.name}</p>
            </div>
            <div className={'flex flex-col gap-4'}>
                <div className={'flex gap-2 items-center'}>
                    <Codesandbox className={'text-alto-dark group-hover:text-white w-3 h-3 text-sm'} />
                    <p className={'text-xs group-hover:text-white '}>{device.name}</p>
                </div>
                <div className={'flex gap-2 items-center'}>
                    <MapPin className={'text-alto-dark group-hover:text-white w-3 h-3 text-sm'} />
                    <p className={'text-xs group-hover:text-white'}>{device.typeCode}</p>
                </div>
            </div>
        </Card>
    )
}

export default DeviceListItemView
