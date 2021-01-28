package org.ahivs.sample.di.modules

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import org.ahivs.base.di.viewmodel.ViewModelKey
import org.ahivs.login.presentation.LoginViewModel
import org.ahivs.sample.home.HomeViewModel


@Module
abstract class ViewModels {
    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun provideHomeViewModel(homeViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun provideLoginViewModel(loginViewModel: LoginViewModel): ViewModel
}