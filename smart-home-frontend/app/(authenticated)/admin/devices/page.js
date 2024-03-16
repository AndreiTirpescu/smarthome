'use client'

import React from 'react'
import { useFetchDevices } from '@/smarthome/features/devices/hooks/useFetchDevices'
import ItemsView from '@/smarthome/components/view/ListView'
import DeviceItemComponentListCard from '@/smarthome/features/devices/components/DeviceItemComponentListCard'
import DevicePreviewItemComponent from '@/smarthome/features/devices/components/DevicePreviewItemComponent'

const AdminDeviceManagementPage = () => {
    const { isLoading, devices } = useFetchDevices(0, 100)

    return (
        <div className={'flex w-full min-h-screen gap-4'}>
            <div className={'w-full sm:w-1/2'}>
                {!isLoading && <ItemsView items={devices} propName={'device'} itemComponent={DeviceItemComponentListCard} />}
            </div>
            {!isLoading && <DevicePreviewItemComponent device={devices[1]} />}
        </div>
    )
}

export default AdminDeviceManagementPage
