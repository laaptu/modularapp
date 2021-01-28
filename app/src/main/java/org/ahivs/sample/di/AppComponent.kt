package org.ahivs.sample.di

import org.ahivs.sample.di.modules.ActivityModules
import org.ahivs.sample.di.modules.FragmentModules
import dagger.Component
import dagger.android.AndroidInjectionModule
import org.ahivs.base.di.AppScope
import org.ahivs.base.di.BaseComponent
import org.ahivs.sample.MainApplication
import org.ahivs.sample.di.modules.ApiModule
import org.ahivs.sample.di.modules.RepositoryModule
import org.ahivs.sample.di.modules.ViewModels

@Component(modules = [AndroidInjectionModule::class, AppModule::class, ActivityModules::class,
    FragmentModules::class, ViewModels::class, ApiModule::class, RepositoryModule::class],
        dependencies = [BaseComponent::class])
@AppScope
interface AppComponent {
    fun injectOnApp(mainApplication: MainApplication)
    @Component.Builder
    interface Builder {
        fun addBaseComponent(baseComponent: BaseComponent): Builder
        fun build(): AppComponent
    }
}