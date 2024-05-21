import React from 'react'
import { renderCell } from '@/smarthome/components/table/AppTableCell'

const AppTableRows = ({ items, cellConfig, headers }) => {
    return (
        <tbody className={'table-auto text-sm'}>
            {items.map(item => (
                <tr key={item.id} className={'shadow-sm'}>
                    {cellConfig.map((cfg, idx) => (
                        <td key={`${item.id}_${cfg.header}`} className={'border-t border-b border-gray-600 py-4'}>
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
