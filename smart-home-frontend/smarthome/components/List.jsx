import React from 'react'

export default function List ({ items, propName, props, itemComponent: ItemComponent }) {
    return (
        <ul className={'w-full flex flex-row gap-2 flex flex-col'}>
            {
                items.map(item => <ItemComponent key={ item.key } { ...{ [propName]: item, ...props } }/>)
            }
        </ul>
    )
}
