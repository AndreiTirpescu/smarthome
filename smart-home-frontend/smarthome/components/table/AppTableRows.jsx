import React from 'react'
import { renderCell } from '@/smarthome/components/table/AppTableCell'

const AppTableRows = ({ items, cellConfig, headers }) => {
    return (
        <tbody className={'table-auto text-sm'}>
            {items.map(item => (
                <tr key={item.id} className={'shadow-sm'}>
                    {cellConfig.map((cfg, idx) => (
                        <td key={`${item.id}_${cfg.header}`} className={`${idx === 0 && 'border-l rounded-l-md'} ${idx === headers.length - 1 && 'border-r rounded-r-md'} border-t border-b border-alto p-4`}>
                            {
                                renderCell(item, cfg)
                            }
                        </td>
                    ))}
                </tr>
            ))}
        </tbody>
    )
}

export default AppTableRows
