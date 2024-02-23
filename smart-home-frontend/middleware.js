import { NextResponse } from 'next/server'
import { cookies } from 'next/headers'
import { jwtDecode } from 'jwt-decode'

const authenticated = []
const admin = ['/admin']

const matchesRouteAnyRoute = (req, routes) => {
    return routes.reduce((result, item) => {
        return result && req.startsWith(item)
    }, routes.length > 0)
}

const handleAuthenticatedRoutes = (accessToken, request) => {
    if (!accessToken) {
        return NextResponse.redirect(new URL('/login', request.url))
    }
}

function handleAdminRoutes (accessToken, request, role) {
    if (!accessToken) {
        return NextResponse.redirect(new URL('/login', request.url))
    }

    if (role !== 'ADMIN') {
        return NextResponse.redirect(new URL('/', request.url))
    }
}

export default function middleware (request) {
    const { pathname } = request.nextUrl
    const cookieStore = cookies()
    const accessToken = cookieStore.get('accessToken')?.value
    const role = accessToken ? jwtDecode(accessToken).role : null
    const isExpired = accessToken ? jwtDecode(accessToken).exp > new Date() : true
    const isPendingActivation = accessToken ? jwtDecode(accessToken).status === 'PENDING_ACTIVATION' : false

    if (pathname.startsWith('/_next')) return NextResponse.next()

    if (accessToken && isExpired) {
        cookieStore.delete('accessToken')
        cookieStore.delete('refreshToken')
        return NextResponse.redirect(new URL('/login'))
    }

    if (accessToken && isPendingActivation && !pathname.startsWith('/activate')) {
        return NextResponse.redirect(new URL('/activate', request.url))
    }

    if (!accessToken && pathname.startsWith('/activate')) {
        return NextResponse.redirect(new URL('/login', request.url))
    }

    if ((pathname === '/' || request.nextUrl.pathname === '') && !accessToken) {
        return NextResponse.redirect(new URL('/login', request.url))
    }

    if (matchesRouteAnyRoute(pathname, authenticated)) {
        return handleAuthenticatedRoutes(accessToken, request)
    }

    if (matchesRouteAnyRoute(request.nextUrl.pathname, admin)) {
        return handleAdminRoutes(accessToken, request, role)
    }

    if (request.nextUrl.pathname.startsWith('/login') && accessToken) {
        return NextResponse.redirect(new URL('/', request.url))
    }
}
