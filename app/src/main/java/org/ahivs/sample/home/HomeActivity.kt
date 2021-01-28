package org.ahivs.sample.home

import android.content.Intent
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import org.ahivs.base.di.view.ViewModelActivity
import org.ahivs.login.presentation.LoginActivity
import org.ahivs.sample.R
import retrofit2.Retrofit
import javax.inject.Inject

class HomeActivity : ViewModelActivity<HomeViewModel>(R.layout.activity_home) {

    override val viewModel: HomeViewModel by viewModels {
        viewModelFactory
    }

    fun goToLogin(view: View) {
        startActivity(Intent(this, LoginActivity::class.java))
    }
}

//TODO: remove this once actual HomeUseCase is created
//this is just for example
class HomeViewModel @Inject constructor(val homeUseCase: HomeUseCase) : ViewModel()

class HomeUseCase @Inject constructor(val retrofit: Retrofit, val gson: Gson) {
    fun printHomeUserCase() {
    }
}