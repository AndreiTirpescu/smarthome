/** @type {import('tailwindcss').Config} */
module.exports = {
    content: [
        './pages/**/*.{js,ts,jsx,tsx,mdx}',
        './components/**/*.{js,ts,jsx,tsx,mdx}',
        './app/**/*.{js,ts,jsx,tsx,mdx}',
        './smarthome/**/*.{js,ts,jsx,tsx,mdx}'
    ],
    theme: {
        extend: {
            colors: {
                alto: {
                    DEFAULT: '#D3D3D3',
                    light: '#F7F7F7',
                    dark: '#8B8E92'
                },
                secondary: '#040404',
                primary: '#F4F5F9',
                accent: {
                    DEFAULT: '#3A46C8',
                    hover: '#202772',
                    active: '#5A64D1'
                }
            },
            keyframes: {
                'slide-in': {
                    '0%': {
                        transform: 'translateX(100%)',
                        opacity: 0
                    },
                    '100%': {
                        transform: 'translateX(0)',
                        opacity: 1
                    }
                }
            }
        }
    },
    plugins: []
}
