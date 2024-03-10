import React from 'react'

const DeviceListItemView = ({ device }) => {
    return (
        <div className={'w-full px-4 py-2 rounded-md flex gap-14 border-alto border shadow-sm items-center bg-white'}>
            <img alt={'Device Image Url'} src={device.imageUrl} className={'rounded-md w-32 h-32 md:w-56 md:h-56 object-contain shadow-lg border border-alto'} />
            <div className={'w-full flex flex-col gap-8'}>
                <div className={'flex flex-col'}>
                    <p className={'font-bold text-sm text-accent'}>{device.name}</p>
                    <p className={'text-gray-400 font-semibold text-sm'}>{device.typeCode}</p>
                </div>
                <p className={'text-sm'}>{device.shortDescription}</p>
                <p className={'hidden text-sm text-justify line-clamp-1 md:line-clamp-3'}>{device.description}</p>
            </div>
        </div>
    )
}

export default DeviceListItemView
