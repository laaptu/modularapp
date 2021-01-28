package org.ahivs.base.di

import org.ahivs.base.config.AppConfiguration
import org.ahivs.base.config.NetworkConfig
import org.ahivs.base.serializer.GsonAdapters
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/*
 *
 * TODO: These are just samples, replace with dependencies that is required by all features
 *  This module is for all the dependencies that may require by any new feature module.
 *  Right now there is only an app module dependent on it, in future other feature modules may depenend
 *  on this one
 */
@Module
class BaseModule {

    @Provides
    @Singleton
    fun provideGson(gsonAdapters: GsonAdapters): Gson {
        val gsonBuilder = GsonBuilder()
        gsonAdapters.getDeserializerTypeAdapters().forEach { (key, value) ->
            gsonBuilder.registerTypeAdapter(key, value)
        }
        gsonAdapters.getTypeAdapters().forEach { (key, value) ->
            gsonBuilder.registerTypeAdapter(key, value)
        }
        return gsonBuilder.create()
    }


    //TODO: this will later come from a Helper class and need to pass this to base module
    @Provides
    @Singleton
    fun provideAppConfiguration(): AppConfiguration = AppConfiguration.getConfiguration()

    @Provides
    @Singleton
    fun provideNetworkConfig(appConfiguration: AppConfiguration): NetworkConfig = appConfiguration.networkConfig
}