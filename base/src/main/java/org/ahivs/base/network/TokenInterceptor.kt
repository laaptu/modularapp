package org.ahivs.base.network

import org.ahivs.base.cache.TokenRepository
import okhttp3.*
import java.io.IOException
import javax.inject.Inject

//Customize this to pass any headers to the request or authenticated params if 401 is found
class TokenInterceptor @Inject constructor(private val tokenRepository: TokenRepository) : Interceptor, Authenticator {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        tokenRepository.getAuthenticatedHeader().forEach { (key, value) ->
            requestBuilder.addHeader(key, value)
        }
        return chain.proceed(requestBuilder.build())
    }

    override fun authenticate(route: Route?, response: Response): Request? {
        return try {
            val requestBuilder = response.request.newBuilder()
            tokenRepository.getRefreshedAuthenticatedHeader().forEach { (key, value) ->
                requestBuilder.addHeader(key, value)
            }
            requestBuilder.build()
        } catch (obj: IOException) {
            null
        }
    }
}