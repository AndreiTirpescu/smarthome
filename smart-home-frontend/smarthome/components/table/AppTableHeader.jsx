import React from 'react'

const AppTableHeader = ({ headers }) => {
    return (
        <thead className={'table-auto bg-accent text-primary'}>
            <tr className={'capitalize shadow-sm'}>
                {
                    headers.map((header, idx) => (
                        <th key={header.name} className={`text-start ${idx === 0 && 'rounded-l-md'} ${idx === headers.length - 1 && 'rounded-r-md'} p-4 select-none`}>
                            {header.name}
                        </th>
                    ))
                }
            </tr>
        </thead>
    )
}

export default AppTableHeader
