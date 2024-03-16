import React, { useEffect } from 'react'
import { useFetchDeviceEvents } from '@/smarthome/features/devices/hooks/useFetchDeviceEvents'
import eventsTableConfig from '@/smarthome/features/devices/components/deviceEventsTableConfiguration'
import AppTable from '@/smarthome/components/table/AppTable'
import { useFetchDeviceValues } from '@/smarthome/features/devices/hooks/useFetchDeviceValues'
import valuesTableConfig from '@/smarthome/features/devices/components/deviceValuesTableConfiguration'

const DevicePreviewItemComponent = ({ device }) => {
    const { eventsCells, eventsHeaders } = eventsTableConfig
    const { valuesCells, valuesHeaders } = valuesTableConfig
    const { deviceEvents, isLoadingEvents } = useFetchDeviceEvents(device.id)
    const { deviceValues, isLoadingValues } = useFetchDeviceValues(device.id)

    useEffect(() => {
        console.log(device.id)
    }, [])

    return (
        <div className={'w-full hidden min-h-screen sm:flex flex-col px-8 py-4 gap-6 rounded-md border border-alto bg-white'}>
            <div className={'w-full flex items-center justify-center'}>
                <img alt={''} src={device.imageUrl} className={'rounded-md w-56 shadow-xl h-56 object-cover'} />
            </div>
            <div className={'flex flex-col'}>
                <p className={'text-sm font-semibold text-accent'}>{device.name}</p>
                <p className={'text-xs text-alto-dark'}>{device.typeCode}</p>
            </div>
            <p className={'text-justify text-sm'}>{device.shortDescription}</p>
            <p className={'text-justify text-sm'}>{device.description}</p>

            {!isLoadingEvents && <div className={'w-full border border-alto rounded-md flex flex-col'}>
                <p className={'text-accent text-xs font-bold px-4 py-4'}>Events</p>
                <AppTable items={deviceEvents} cellConfig={[...eventsCells]} headerConfig={eventsHeaders} />
            </div>}
            {!isLoadingValues && <div className={'w-full border border-alto rounded-md flex flex-col'}>
                <p className={'text-accent text-xs font-bold px-4 py-4'}>Values</p>
                <AppTable items={deviceValues} cellConfig={[...valuesCells]} headerConfig={valuesHeaders} />
            </div>}
        </div>
    )
}

export default DevicePreviewItemComponent
