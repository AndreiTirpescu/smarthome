/** @type {import('next').NextConfig} */
const nextConfig = {};

nextConfig.i18n = {
    locales: ['en'],
    defaultLocale: 'en'
}

nextConfig.env = {
    BACKEND_BASE_URL_API_V1: process.env.BACKEND_BASE_URL_API_V1
}

export default nextConfig;
