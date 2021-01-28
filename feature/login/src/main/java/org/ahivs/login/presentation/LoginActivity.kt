package org.ahivs.login.presentation

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import org.ahivs.login.presentation.data.LoggedInUserView
import kotlinx.android.synthetic.main.activity_login.*
import org.ahivs.base.di.view.ViewModelActivity
import org.ahivs.base.ui.afterTextChanged
import org.ahivs.base.ui.getStringFromResource
import org.ahivs.login.R


class LoginActivity : ViewModelActivity<LoginViewModel>(R.layout.activity_login) {

    override val viewModel: LoginViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observerViewModel()
        initUI()
    }

    private fun initUI() {
        txtUname.afterTextChanged {
            viewModel.inputDataChangedForUname(
                txtUname.text.toString()
            )
        }

        txtPwd.apply {
            afterTextChanged {
                viewModel.inputDataChangeForPwd(
                    txtPwd.text.toString()
                )
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        initiateLogin(
                            txtUname.text.toString(),
                            txtPwd.text.toString()
                        )
                }
                false
            }
        }

        btnLogin.setOnClickListener {
            initiateLogin(txtUname.text.toString(), txtPwd.text.toString())
        }
    }

    private fun observerViewModel() {
        viewModel.btnEnabledState.observe(this) {
            btnLogin.isEnabled = it
        }

        viewModel.unameErrorState.observe(this) {
            txtUnameInputLayout.error = it.error?.let { unameError ->
                getStringFromResource(unameError)
            }
        }

        viewModel.pwdErrorState.observe(this) {
            txtPwdInputLayout.error = it.error?.let { pwdError ->
                getStringFromResource(pwdError)
            }
        }

        viewModel.progressState.observe(this) { inProgress ->
            if (inProgress) {
                pbLoading.visibility = View.VISIBLE
                txtPwdInputLayout.isEnabled = false
                txtUnameInputLayout.isEnabled = false;
            } else {
                pbLoading.visibility = View.GONE
                txtPwdInputLayout.isEnabled = true
                txtUnameInputLayout.isEnabled = true;
            }
        }

        viewModel.loginResult.observe(this@LoginActivity, Observer {
            val loginResult = it ?: return@Observer

            if (loginResult.error != null) {
                val errorMsg = loginResult.errorMsg ?: getStringFromResource(loginResult.error)
                showLoginFailed(errorMsg)
            }
            if (loginResult.success != null) {
                updateUiWithUser(loginResult.success)
                setResult(Activity.RESULT_OK)
                finish()
            }
        })
    }

    private fun initiateLogin(username: String, password: String) {
        viewModel.initLogin(username, password)
    }

    private fun updateUiWithUser(model: LoggedInUserView) {
        val welcome = getString(R.string.welcome)
        val displayName = model.displayName
        // TODO : initiate successful logged in experience
        Toast.makeText(
            applicationContext,
            "$welcome $displayName",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun showLoginFailed(errorMsg: String) {
        Toast.makeText(applicationContext, errorMsg, Toast.LENGTH_SHORT).show()
    }
}


