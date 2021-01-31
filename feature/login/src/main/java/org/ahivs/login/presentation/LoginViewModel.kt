package org.ahivs.login.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import org.ahivs.login.domain.LoginUseCase
import org.ahivs.login.presentation.data.LoggedInUserView
import org.ahivs.login.presentation.data.LoginResult
import org.ahivs.login.presentation.data.LoginViewState
import org.ahivs.login.utils.EMAIL_ADDRESS
import kotlinx.coroutines.launch
import org.ahivs.base.error.ApiException
import org.ahivs.login.R
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    val loginViewState: LoginViewState
) : ViewModel() {

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    private val _btnEnabledState = MutableLiveData<Boolean>()
    val btnEnabledState: LiveData<Boolean> = _btnEnabledState

    private val _progressState = MutableLiveData<Boolean>()
    val progressState: LiveData<Boolean> = _progressState

    private val _pwdErrorState = MutableLiveData<LoginViewState.ErrorState>()
    val pwdErrorState: LiveData<LoginViewState.ErrorState> = _pwdErrorState

    private val _unameErrorState = MutableLiveData<LoginViewState.ErrorState>()
    val unameErrorState: LiveData<LoginViewState.ErrorState> = _unameErrorState

    fun initLogin(username: String, password: String) {
        if (!loginViewState.inputValues.isValid ||
            loginViewState.viewState == LoginViewState.ViewState.PROGRESS
        )
            return
        login(username, password)
    }

    private fun login(username: String, password: String) {
        viewModelScope.launch {
            showProgress(true)
            try {
                val loggedInUserView = loginUseCase.invoke(username, password)
                _loginResult.value =
                    LoginResult(success = LoggedInUserView(displayName = loggedInUserView.email))
            } catch (apiException: ApiException) {
                _loginResult.value = LoginResult(
                    error = R.string.login_failed,
                    errorMsg = apiException.apiError?.message
                )
            } finally {
                showProgress(false)
            }
        }
    }

    private fun showProgress(show: Boolean) {
        _progressState.value = show
        _btnEnabledState.value = !show
    }

    fun inputDataChangedForUname(username: String) {
        val errorState = LoginViewState.ErrorState()
        loginViewState.inputValues.uname = username
        if (isUserNameValid(username)) {
            setValidInput(isPasswordValid(loginViewState.inputValues.pwd))
        } else {
            setValidInput(false)
            errorState.error = R.string.invalid_username
        }
        _unameErrorState.value = errorState
    }

    fun inputDataChangeForPwd(pwd: String) {
        val errorState = LoginViewState.ErrorState()
        loginViewState.inputValues.pwd = pwd
        if (isPasswordValid(pwd)) {
            setValidInput(isUserNameValid(loginViewState.inputValues.uname))
        } else {
            setValidInput(false)
            errorState.error = R.string.invalid_password
        }
        _pwdErrorState.value = errorState
    }

    private fun setValidInput(isValid: Boolean) {
        loginViewState.inputValues.isValid = isValid
        _btnEnabledState.value = isValid
    }

    private fun isUserNameValid(username: String?): Boolean =
        username != null && EMAIL_ADDRESS.matcher(username).matches()

    private fun isPasswordValid(password: String?): Boolean =
        password != null && password.length > 5
}
