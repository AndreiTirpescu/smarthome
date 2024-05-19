import React from 'react'

export default function UnauthenticatedPageLayout ({ children }) {
    return (
        <main className={'w-full min-h-screen bg-white flex justify-center items-center bg-cover'}>
            <div className={'relative h-screen w-1/2'}>
                <img src={'smarthome_cover_image.jpg'}
                    className={'w-full h-full absolute z-0 object-cover object-center'}
                    alt={'Banner Image'}/>
                <div className={ 'absolute z-5 bg-gradient-to-t from-black to-transparent w-full h-full opacity-65 ' } />
                <div className={'absolute w-full h-full flex flex-col justify-between'}>
                    <div className={'flex gap-4 p-4'}>
                        <span className={ 'w-[64px] h-[4px] bg-white rounded-full' }/>
                        <span className={ 'w-[64px] h-[4px] rounded-full bg-alto-light' }/>
                        <span className={ 'w-[64px] h-[4px] rounded-full bg-alto-light' }/>
                    </div>

                    <span className={'text-7xl font-medium text-white p-4 select-none'}>Connect with your home with ease using MySmartHome solutions</span>
                </div>
            </div>
            <div className={'h-screen w-1/2'}>
                { children }
            </div>
        </main>
    )
}
