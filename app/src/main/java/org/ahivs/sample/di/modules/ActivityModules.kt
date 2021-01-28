package org.ahivs.sample.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import org.ahivs.base.di.ComponentScope
import org.ahivs.login.presentation.LoginActivity
import org.ahivs.sample.home.HomeActivity

@Module
abstract class ActivityModules {
    @ContributesAndroidInjector
    @ComponentScope
    abstract fun bindsHomeActivity(): HomeActivity

    @ContributesAndroidInjector
    @ComponentScope
    abstract fun bindLoginActivity(): LoginActivity
}