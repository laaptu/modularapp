package org.ahivs.base.network

import okhttp3.Interceptor
import okhttp3.Response

//Customize this to pass necessary query params to each and every API request
class QueryParamInterceptor(private val queryParam: Map<String, String>) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val httpUrl = originalRequest.url
        val httpUrlBuilder = httpUrl.newBuilder()
        queryParam.forEach { (key, value) ->
            httpUrlBuilder.addQueryParameter(key, value)
        }
        return chain.proceed(originalRequest.newBuilder().url(httpUrlBuilder.build()).build())
    }
}