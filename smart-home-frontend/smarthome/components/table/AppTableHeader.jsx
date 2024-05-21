import React from 'react'

const AppTableHeader = ({ headers }) => {
    return (
        <thead className={'table-auto text-base '}>
            <tr className={'capitalize shadow-sm'}>
                {
                    headers.map((header, idx) => (
                        <th key={header.name} className={'text-sm text-start py-4 border-b border-b-gray-600'}>
                            {header.name}
                        </th>
                    ))
                }
            </tr>
        </thead>
    )
}

export default AppTableHeader
