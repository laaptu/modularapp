package org.ahivs.sample.di.modules

import dagger.Module
import dagger.Provides
import org.ahivs.login.domain.LoginApiService
import retrofit2.Retrofit

@Module
class ApiModule {

    @Provides
    fun provideLoginApiService(retrofit: Retrofit): LoginApiService =
            retrofit.create(LoginApiService::class.java)
}
