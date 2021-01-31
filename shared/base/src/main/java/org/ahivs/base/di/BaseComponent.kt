package org.ahivs.base.di

import org.ahivs.base.config.AppConfiguration
import com.google.gson.Gson
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * TODO: These are just samples, replace with dependencies that is required by all features
 */
@Component(modules = [BaseModule::class, NetworkModule::class])
@Singleton
interface BaseComponent {

    @Component.Builder
    interface Builder {
        fun build(): BaseComponent
    }

    fun provideRetrofit(): Retrofit
    fun provideGson(): Gson
    fun provideAppConfiguration(): AppConfiguration
}