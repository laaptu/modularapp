package org.ahivs.base.di

import org.ahivs.base.config.NetworkConfig
import org.ahivs.base.network.QueryParamInterceptor
import org.ahivs.base.network.ResponseInterceptor
import org.ahivs.base.network.TokenInterceptor
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    //Add or remove the interceptors as per the need of the app
    @Provides
    fun provideOkHttpClient(networkConfig: NetworkConfig,
                            responseInterceptor: ResponseInterceptor,
                            httpLoggingInterceptor: HttpLoggingInterceptor,
                            queryParamInterceptor: QueryParamInterceptor,
                            tokenInterceptor: TokenInterceptor
    ): OkHttpClient =
            OkHttpClient.Builder()
                    .addInterceptor(responseInterceptor)
                    .addInterceptor(queryParamInterceptor)
                    .addInterceptor(tokenInterceptor)
                    .addInterceptor(httpLoggingInterceptor)
                    .authenticator(tokenInterceptor)
                    .connectTimeout(networkConfig.connectionTimeOutInSec, TimeUnit.SECONDS)
                    .readTimeout(networkConfig.readTimeOutInSec, TimeUnit.SECONDS)
                    .writeTimeout(networkConfig.writeTimeOutInSec, TimeUnit.SECONDS)
                    .build()

    @Provides
    fun provideLoggingInterceptor(networkConfig: NetworkConfig): HttpLoggingInterceptor =
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.valueOf(networkConfig.httpLoggingLevel)
            }

    @Provides
    fun provideNetworkInterceptor(networkConfig: NetworkConfig): QueryParamInterceptor =
            QueryParamInterceptor(networkConfig.defaultQueryParams)

    @Provides
    @Singleton
    fun provideRetrofit(okhttpClient: OkHttpClient,
                        networkConfig: NetworkConfig,
                        gson: Gson): Retrofit =
            Retrofit.Builder()
                    .client(okhttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .baseUrl(networkConfig.apiBaseUrl)
                    .build()

}