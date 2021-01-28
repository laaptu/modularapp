package org.ahivs.sample

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import org.ahivs.base.config.AppConfiguration
import org.ahivs.base.di.DaggerBaseComponent
import org.ahivs.sample.di.DaggerAppComponent
import javax.inject.Inject

class MainApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var appConfiguration: AppConfiguration

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder().addBaseComponent(
            DaggerBaseComponent.create()
        ).build().injectOnApp(this)
    }
}