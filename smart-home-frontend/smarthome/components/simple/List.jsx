import React from 'react'

export default function List ({ items, propName, className, props, itemComponent: ItemComponent }) {
    return (
        <ul className={`${className ?? ''} gap-8 flex flex-col`}>
            {
                items.map(item => <ItemComponent key={ item.key } { ...{ [propName]: item, ...props } }/>)
            }
        </ul>
    )
}
