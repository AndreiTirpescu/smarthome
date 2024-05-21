'use client'
import React from 'react'
import { useFetchDeviceByTypeCode } from '@/smarthome/features/devices/hooks/useFetchDeviceByTypeCode'
import { usePathname } from 'next/navigation'
import AppTable from '@/smarthome/components/table/AppTable'
import { useFetchDeviceEvents } from '@/smarthome/features/devices/hooks/useFetchDeviceEvents'
import eventsTableConfig from '@/smarthome/features/devices/components/deviceEventsTableConfiguration'
import { useFetchDeviceValues } from '@/smarthome/features/devices/hooks/useFetchDeviceValues'
import valuesTableConfig from '@/smarthome/features/devices/components/deviceValuesTableConfiguration'

const DeviceBreakdown = () => {
    const pathname = usePathname()
    const { device, isLoading } = useFetchDeviceByTypeCode({ typeCode: pathname.replace('/devices/', '') })
    const { events, loadingEvents } = useFetchDeviceEvents(device.id)
    const { values, loadingValues } = useFetchDeviceValues(device.id)

    return (
        <>
            <div className={'flex flex-col gap-6'}>
                <p className={'text-base font-semibold leading-loose'}>Device breakdown - View device details</p>
                {!isLoading && (
                    <p className={'text-base leading-loose text-justify'}>{`${device.description}`}</p>
                )}

                <p className={'text-base font-semibold leading-loose'}>Device Events - See what can happen on this device</p>

                {!loadingEvents && (
                    <AppTable items={events} cellConfig={[...(eventsTableConfig.cells)]} headerConfig={eventsTableConfig.headers} />
                )}

                <p className={'text-base font-semibold leading-loose'}>Device Values - See which values can be set on this device</p>

                {!loadingValues && (
                    <AppTable items={values} cellConfig={[...(valuesTableConfig.cells)]} headerConfig={valuesTableConfig.headers} />
                )}

                <div className={'h-48'} />
            </div>
        </>
    )
}

export default DeviceBreakdown
