'use client'

import React from 'react'
import { useFetchDevices } from '@/smarthome/features/devices/hooks/useFetchDevices'
import ItemsView from '@/smarthome/components/view/ListView'
import DeviceItemComponentListCard from '@/smarthome/features/devices/components/DeviceItemComponentListCard'

const AdminDeviceManagementPage = () => {
    const { isLoading, devices } = useFetchDevices(0, 100)

    return (
        <div className={ 'flex flex-col w-full gap-4 overflow-x-auto' }>
            {!isLoading && <ItemsView items={devices} propName={'device'} itemComponent={DeviceItemComponentListCard} />}
        </div>
    )
}

export default AdminDeviceManagementPage
