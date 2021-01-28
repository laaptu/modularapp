package org.ahivs.sample.di.modules

import dagger.Binds
import dagger.Module
import org.ahivs.login.domain.LoginRepository
import org.ahivs.login.domain.LoginRepositoryImpl

@Module
abstract class RepositoryModule {
    @Binds
    abstract fun bindLoginRepository(loginRepositoryImpl: LoginRepositoryImpl): LoginRepository
}