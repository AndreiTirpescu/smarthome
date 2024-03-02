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
                    DEFAULT: '#E8E8E8',
                    light: '#F2F2F2'
                },
                secondary: '#040404',
                primary: '#FFFFFF',
                accent: {
                    DEFAULT: '#3dabff',
                    hover: '#1b88f5',
                    active: '#1470e1'
                }
            }
        }
    },
    plugins: []
}
