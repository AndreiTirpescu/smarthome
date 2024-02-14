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
    const cookieStore = cookies()
    const accessToken = cookieStore.get('accessToken')?.value
    const role = accessToken ? jwtDecode(accessToken).role : null

    if ((request.nextUrl.pathname === '/' || request.nextUrl.pathname === '') && !accessToken) {
        return NextResponse.redirect(new URL('/login', request.url))
    }

    if (matchesRouteAnyRoute(request.nextUrl.pathname, authenticated)) {
        return handleAuthenticatedRoutes(accessToken, request)
    }

    if (matchesRouteAnyRoute(request.nextUrl.pathname, admin)) {
        return handleAdminRoutes(accessToken, request, role)
    }

    if (request.nextUrl.pathname.startsWith('/login') && accessToken) {
        return NextResponse.redirect(new URL('/', request.url))
    }
}
