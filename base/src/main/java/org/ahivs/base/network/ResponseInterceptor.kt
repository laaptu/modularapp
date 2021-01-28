package org.ahivs.base.network

import org.ahivs.base.error.ExceptionMapper
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

//Customize this to map generic errors for the API
class ResponseInterceptor @Inject constructor(private val exceptionMapper: ExceptionMapper) : Interceptor {
    //not an elegant solution, just for a demo purpose
    override fun intercept(chain: Interceptor.Chain): Response {
        try {
            val response = chain.proceed(chain.request())
            if (response.isSuccessful)
                return response
            throw exceptionMapper.mapApiErrorResponse(response)
        } catch (exception: Exception) {
            throw exceptionMapper.mapApiException(exception)
        }
    }
}