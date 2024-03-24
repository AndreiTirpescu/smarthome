import React from 'react'

const AppTableCell = ({ itemComponent: ItemComponent, props }) => {
    return (
        <>
            <ItemComponent { ...props } />
        </>
    )
}

const DefaultCellRenderer = ({ value }) => {
    return (
        <>
            <span className={'text-sm text-start'}>{value}</span>
        </>
    )
}

const withDefaultCellRenderer = (keys, item) => {
    const DefaultAppTableCell = ({ key, value }) => {
        const props = { [key]: value }

        return (
            <AppTableCell itemComponent={DefaultCellRenderer} props={{ ...props }} />
        )
    }

    DefaultAppTableCell.displayName = 'DefaultCellRenderer'

    return <DefaultCellRenderer key={'value'} value={item[keys[0]]} />
}

const withCustomComponent = (item, cfg) => {
    const Component = cfg.component

    const CustomCellRenderer = ({ item, cfg }) => {
        const props = cfg.keys.map(key => {
            return { [key]: item[key] }
        }).reduce((acc, curr) => Object.assign(acc, curr), {})

        return (
            <AppTableCell itemComponent={Component} props={{ ...props }} />
        )
    }

    CustomCellRenderer.displayName = 'DefaultCellRenderer'

    return <CustomCellRenderer item={item} cfg={cfg} />
}

export const renderCell = (item, cellConfig) => {
    if (!cellConfig.component) {
        return withDefaultCellRenderer([cellConfig.keys], item)
    }

    return withCustomComponent(item, cellConfig)
}

export default AppTableCell
