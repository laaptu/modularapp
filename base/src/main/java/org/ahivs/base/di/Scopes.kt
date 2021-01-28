package org.ahivs.base.di

import javax.inject.Scope


/**
 *  This is a app scope.This is similar to Singleton.
 *  It is just for clear understanding, nothing fancy
 */
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class AppScope


/**
 *  This is a feature scope. Any new feature module will use this scope
 *  for dependencies. It is just for clear understanding, nothing fancy
 */
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ComponentScope