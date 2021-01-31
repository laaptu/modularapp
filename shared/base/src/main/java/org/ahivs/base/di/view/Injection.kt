package org.ahivs.base.di.view

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.ViewModel
import org.ahivs.base.di.inject
import dagger.android.AndroidInjection
import org.ahivs.base.di.viewmodel.ViewModelProviderFactory
import org.ahivs.base.ui.BaseActivity
import org.ahivs.base.ui.BaseFragment
import javax.inject.Inject

abstract class ViewModelActivity<T : ViewModel>(contentLayoutId: Int = 0) :
    InjectionActivity(contentLayoutId) {
    @Inject
    lateinit var viewModelFactory: ViewModelProviderFactory
    abstract val viewModel: T
}

abstract class ViewModelFragment<T : ViewModel>(contentLayoutId: Int = 0) :
    InjectionFragment(contentLayoutId) {
    @Inject
    lateinit var viewModelFactory: ViewModelProviderFactory
    abstract val viewModel: T
}

abstract class InjectionActivity(contentLayoutId: Int = 0) : BaseActivity(contentLayoutId) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
    }
}

abstract class InjectionFragment(contentLayoutId: Int = 0) :
    BaseFragment(contentLayoutId) {
    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.inject(this)
    }
}

