package org.ahivs.login.presentation.data

import javax.inject.Inject

class LoginViewState @Inject constructor() {
    var viewState: ViewState = ViewState.INPUT
    var inputValues: InputValues = InputValues()

    enum class ViewState {
        INPUT, PROGRESS
    }

    class InputValues(var uname: String = "", var pwd: String = "", var isValid: Boolean = false)

    class ErrorState(var error: Int? = null)
}
