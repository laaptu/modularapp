package org.ahivs.base.di

import android.content.Context
import dagger.android.HasAndroidInjector

fun Context.inject(obj: Any) {
    if (applicationContext is HasAndroidInjector)
        (applicationContext as HasAndroidInjector).androidInjector().inject(obj)
    else
        throw RuntimeException("Application does not implement ${HasAndroidInjector::class.java.canonicalName}")
}