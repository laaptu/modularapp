package org.ahivs.login.presentation

import org.ahivs.base.error.ApiException
import org.ahivs.base.test.BaseViewModelTest
import org.ahivs.base.test.observeForTesting
import org.ahivs.login.R
import org.ahivs.login.domain.LoginRepository
import org.ahivs.login.domain.LoginUseCase
import org.ahivs.login.domain.data.LoggedInUser
import org.ahivs.login.presentation.data.LoginViewState
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import java.util.stream.Stream


class LoginViewModelTest : BaseViewModelTest() {

    @Mock
    lateinit var loginRepository: LoginRepository
    lateinit var loginUseCase: LoginUseCase
    lateinit var loginViewModel: LoginViewModel
    lateinit var loginViewState: LoginViewState

    @BeforeEach
    fun init() {
        MockitoAnnotations.initMocks(this)
        loginUseCase = LoginUseCase(loginRepository)
        loginViewState = LoginViewState()
        loginViewModel = LoginViewModel(loginUseCase, loginViewState)
    }

    @DisplayName("GIVEN invalid emails, WHEN inputDataChangedForUname is called, " +
            "THEN username error should be observed ")
    @ParameterizedTest(name = " invalid email: {0} ")
    @MethodSource("getInvalidEmails")
    fun invalidEmailMustGiveError(invalidEmail: String) {
        loginViewModel.inputDataChangedForUname(invalidEmail)
        loginViewModel.unameErrorState.observeForTesting {
            val errorState = loginViewModel.unameErrorState.value
            assertNotNull(errorState)
            assertEquals(R.string.invalid_username, errorState?.error)
        }
    }

    @DisplayName("GIVEN invalid emails, WHEN inputDataChangedForUname is called, " +
            "THEN ButtonEnabled state must be false")
    @ParameterizedTest(name = " invalid email: {0} ")
    @MethodSource("getInvalidEmails")
    fun invalidEmailMustDisableButton(invalidEmail: String) {
        loginViewModel.inputDataChangedForUname(invalidEmail)
        loginViewModel.btnEnabledState.observeForTesting {
            val buttonState = loginViewModel.btnEnabledState.value
            assertNotNull(buttonState)
            assertEquals(false, buttonState)
        }
    }

    @DisplayName("GIVEN invalid password, WHEN inputDataChangedForPwd is called, " +
            "THEN password error should be observed ")
    @ParameterizedTest(name = " invalid password: {0} ")
    @MethodSource("getInvalidPasswords")
    fun invalidPasswordMustGiveError(invalidPwd: String) {
        loginViewModel.inputDataChangeForPwd(invalidPwd)
        loginViewModel.pwdErrorState.observeForTesting {
            val errorState = loginViewModel.pwdErrorState.value
            assertNotNull(errorState)
            assertEquals(R.string.invalid_password, errorState?.error)
        }
    }

    @DisplayName("GIVEN invalid password, WHEN inputDataChangedForPwd is called, " +
            "THEN ButtonEnabled state must be false")
    @ParameterizedTest(name = " invalid password: {0} ")
    @MethodSource("getInvalidPasswords")
    fun invalidPasswordMustDisableButton(invalidPwd: String) {
        loginViewModel.inputDataChangeForPwd(invalidPwd)
        loginViewModel.btnEnabledState.observeForTesting {
            val buttonState = loginViewModel.btnEnabledState.value
            assertNotNull(buttonState)
            assertEquals(false, buttonState)
        }
    }

    @DisplayName("GIVEN valid emails, WHEN inputDataChangedForUname is called, " +
            "THEN username error should NOT be observed ")
    @ParameterizedTest(name = " valid email: {0} ")
    @MethodSource("getValidEmails")
    fun validEmailMustNotGiveError(validEmail: String) {
        loginViewModel.inputDataChangedForUname(validEmail)
        loginViewModel.unameErrorState.observeForTesting {
            val errorState = loginViewModel.unameErrorState.value
            assertNull(errorState?.error)
        }
    }

    @DisplayName("GIVEN valid password, WHEN inputDataChangedForPwd is called, " +
            "THEN password error should NOT be observed ")
    @ParameterizedTest(name = " valid password: {0} ")
    @MethodSource("getValidPasswords")
    fun validPasswordMustNotGiveError(validPwd: String) {
        loginViewModel.inputDataChangeForPwd(validPwd)
        loginViewModel.pwdErrorState.observeForTesting {
            val errorState = loginViewModel.pwdErrorState.value
            assertNull(errorState?.error)
        }
    }

    @DisplayName("GIVEN valid email and password, WHEN inputDataChangedForUname and inputDataChangedForPwd is called," +
            "THEN LoginViewState must have valid input values")
    @ParameterizedTest(name = "{0}: {1}")
    @MethodSource("getValidEmailAndPassword")
    fun validEmailAndPasswordMustSetValidInputValues(email: String, pwd: String) {
        loginViewModel.inputDataChangedForUname(email)
        loginViewModel.inputDataChangeForPwd(pwd)
        loginViewModel.btnEnabledState.observeForever { enabled ->
            //avoid writing multiple test checks in a single function
            assertNotNull(enabled)
            assertTrue(enabled)
            assertEquals(email, loginViewModel.loginViewState.inputValues.uname)
            assertEquals(pwd, loginViewModel.loginViewState.inputValues.pwd)
            assertEquals(true, loginViewModel.loginViewState.inputValues.isValid)
        }
    }

    @DisplayName("GIVEN valid email and password, WHEN initLogin is called, THEN" +
            " valid LoggedInUser with given email must be returned")
    @Test
    fun validLoggedInUserMustBeReturnedForValidEmailAndPassword() {
        loginViewModel.inputDataChangedForUname(validEmail)
        loginViewModel.inputDataChangeForPwd(validPwd)

        runBlockingTest {
            `when`(loginRepository.login(validEmail, validPwd)).thenReturn(validLoggedInUser)
        }

        loginViewModel.initLogin(validEmail, validPwd)

        loginViewModel.loginResult.observeForever { loginResult ->
            assertNotNull(loginResult)
            assertEquals(validLoggedInUser.email, loginResult.success?.displayName)
        }
    }

    @DisplayName("GIVEN incorrect email OR password, WHEN initLogin is called, THEN" +
            " valid login error must return")
    @Test
    fun loginErrorMustBeReturnedForInValidEmailOrPassword() {
        val incorrectPwd = "123456789000"
        loginViewModel.inputDataChangedForUname(validEmail)
        loginViewModel.inputDataChangeForPwd(incorrectPwd)

        runBlockingTest {
            `when`(loginRepository.login(validEmail, incorrectPwd)).thenAnswer {
                throw ApiException()
            }
        }

        loginViewModel.initLogin(validEmail, incorrectPwd)

        loginViewModel.loginResult.observeForever { loginResult ->
            assertNotNull(loginResult)
            assertEquals(R.string.login_failed, loginResult.error)
        }
    }

    companion object {
        @JvmStatic
        private fun getInvalidEmails(): Stream<String> = Stream.of("abc4", "", "a@c.", "123")

        @JvmStatic
        private fun getInvalidPasswords(): Stream<String> = Stream.of("", "123", "12345")

        @JvmStatic
        private fun getValidEmails(): Stream<String> = Stream.of("abc@c.om", "test@test.com", "a@b.com")

        @JvmStatic
        private fun getValidPasswords(): Stream<String> = Stream.of("abcdef", "123456", "zxy123")

        @JvmStatic
        private fun getValidEmailAndPassword(): Stream<Arguments> =
                Stream.of(
                        Arguments.of("test@test.com", "123456"),
                        Arguments.of("abc@c.com", "zxy1244"),
                        Arguments.of("a@b.com", "abcdef")
                )

        const val validEmail = "test@test.com"
        const val validPwd = "123456"
        val validLoggedInUser = LoggedInUser(email = validEmail,
                displayName = "",
                idToken = "890",
                refreshToken = "909")
    }

}