package org.ahivs.sample.di.modules

import org.ahivs.sample.home.SampleFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector
import org.ahivs.base.di.ComponentScope

@Module
abstract class FragmentModules {
    @ContributesAndroidInjector
    @ComponentScope
    abstract fun bindHomeFragment(): SampleFragment
}