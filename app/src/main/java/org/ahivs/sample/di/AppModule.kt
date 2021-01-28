package org.ahivs.sample.di

import dagger.Module

/**
 * This is just for demonstration and can change
 * AppModule is kind of a feature module, where as BaseModule is kind of a
 * core module which provides components to all the feature module. Since there are
 * only app and base module, the AppModule and BaseModule may look like similar*/
@Module
class AppModule