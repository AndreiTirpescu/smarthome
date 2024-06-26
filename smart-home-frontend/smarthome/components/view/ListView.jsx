import React from 'react'

const ListView = ({ items, propName, itemComponent: ItemComponent }) => {
    return (
        <div className={ 'w-full flex flex-col gap-6' }>
            {
                items.map(item => <ItemComponent key={ item.id } { ...{ [propName]: item } }/>)
            }
        </div>
    )
}

export const ClickableListView = ({ items, onItemClicked, propName, itemComponent: ItemComponent }) => {
    return (
        <div className={ 'w-full flex flex-col gap-6' }>
            {
                items.map(item => <ItemComponent onClick={() => onItemClicked(item.id)} key={ item.id } { ...{ [propName]: item } }/>)
            }
        </div>
    )
}

export default ListView
