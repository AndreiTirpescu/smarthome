import React from 'react'
import { useFetchDeviceEvents } from '@/smarthome/features/devices/hooks/useFetchDeviceEvents'
import eventsTableConfig from '@/smarthome/features/devices/components/deviceEventsTableConfiguration'
import AppTable from '@/smarthome/components/table/AppTable'
import { useFetchDeviceValues } from '@/smarthome/features/devices/hooks/useFetchDeviceValues'
import valuesTableConfig from '@/smarthome/features/devices/components/deviceValuesTableConfiguration'
import Card from '@/smarthome/components/simple/Card'

const DevicePreviewItemComponent = ({ device }) => {
    const { cells, headers } = eventsTableConfig
    const { cells, headers } = valuesTableConfig
    const { events, loadingEvents } = useFetchDeviceEvents(device.id)
    const { values, loadingValues } = useFetchDeviceValues(device.id)

    return (
        <Card className={'hidden min-h-screen h-full lg:flex flex-col gap-8 rounded-md bg-white'}>
            <div className={'w-full flex items-center justify-center'}>
                <img alt={''} src={device.imageUrl} className={'rounded-md w-56 shadow-xl h-56 object-cover'} />
            </div>
            <div className={'flex flex-col'}>
                <p className={'text-sm font-semibold text-accent'}>{device.name}</p>
                <p className={'text-xs text-alto-dark'}>{device.typeCode}</p>
            </div>
            <p className={'text-justify text-sm'}>{device.shortDescription}</p>
            <p className={'text-justify text-sm'}>{device.description}</p>

            {!loadingEvents && <Card className={'shadow-lg flex flex-col'}>
                <p className={'text-accent text-xs font-semibold'}>Events</p>
                <AppTable items={events} cellConfig={[...cells]} headerConfig={headers} />
            </Card>}
            {!loadingValues && <Card className={'shadow-lg flex flex-col'}>
                <p className={'text-accent text-xs font-semibold'}>Values</p>
                <AppTable items={values} cellConfig={[...cells]} headerConfig={headers} />
            </Card>}
        </Card>
    )
}

export default DevicePreviewItemComponent
