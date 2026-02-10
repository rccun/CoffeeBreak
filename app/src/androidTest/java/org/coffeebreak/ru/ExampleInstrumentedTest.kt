package org.coffeebreak.ru

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.coffeebreak.domain.model.UserModel
import org.coffeebreak.domain.repository.AuthRepository
import org.coffeebreak.domain.usecase.auth.EmailUseCase
import org.coffeebreak.domain.usecase.auth.PasswordUseCase
import org.coffeebreak.domain.usecase.auth.SignUpUseCase
import org.coffeebreak.domain.usecase.auth.ValidateUseCase
import org.coffeebreak.domain.utils.CustomResult
import org.coffeebreak.domain.utils.EmailValidator
import org.coffeebreak.ru.signup.SignUpScreen
import org.coffeebreak.ru.signup.SignUpViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Rule
    @JvmField
    public val rule = createComposeRule()


    lateinit var signUpViewModel: SignUpViewModel

    @Before
    fun initViewModel() {
        val repo: AuthRepository = AuthRepoImpl()
        val emailRepo: EmailValidator = emailValidatorImpl()
        val useCase = SignUpUseCase(repo)
        val emailUseCase = EmailUseCase(emailRepo)
        val passwordUseCase = PasswordUseCase()
        val valUseCase = ValidateUseCase( passwordUseCase, emailUseCase)
        signUpViewModel = SignUpViewModel(useCase, valUseCase)
    }

    @Test
    fun test1() {
        rule.setContent {
            SignUpScreen(rememberNavController(), signUpViewModel)
        }
        rule.onNodeWithTag("email").performTextInput("a@mail.ru")
        rule.onNodeWithTag("password").performTextInput("12345678Aa@")
        rule.onNodeWithTag("phone").performTextInput("789")
        rule.onNodeWithTag("name").performTextInput("rccun")
        rule.onNodeWithTag("signUpFAB").performClick()

    }
    @Test
    fun test2() {
        rule.setContent {
            SignUpScreen(rememberNavController(), signUpViewModel)
        }
        rule.onNodeWithTag("email").performTextInput("a@mail.ru")
        rule.onNodeWithTag("password").performTextInput("12345678Aa")
        rule.onNodeWithTag("phone").performTextInput("789")
        rule.onNodeWithTag("name").performTextInput("rccun")
        rule.onNodeWithTag("signUpFAB").performClick()
    }
    @Test
    fun test3() {
        rule.setContent {
            SignUpScreen(rememberNavController(), signUpViewModel)
        }
        rule.onNodeWithTag("email").performTextInput("a@mail")
        rule.onNodeWithTag("password").performTextInput("12345678Aa@")
        rule.onNodeWithTag("phone").performTextInput("789")
        rule.onNodeWithTag("name").performTextInput("rccun")
        rule.onNodeWithTag("signUpFAB").performClick()

    }
    @Test
    fun test4() {
        rule.setContent {
            SignUpScreen(rememberNavController(), signUpViewModel)
        }
        rule.onNodeWithTag("email").performTextInput("a")
        rule.onNodeWithTag("password").performTextInput("1")
        rule.onNodeWithTag("phone").performTextInput("7")
        rule.onNodeWithTag("name").performTextInput("r")
        rule.onNodeWithTag("signUpFAB").performClick()
    }
    @Test
    fun test5() {
        rule.setContent {
            SignUpScreen(rememberNavController(), signUpViewModel)
        }
        rule.onNodeWithTag("email").performTextInput("a@mail.ru")
        rule.onNodeWithTag("password").performTextInput("12345678Aa@")
        rule.onNodeWithTag("phone").performTextInput("789")
        rule.onNodeWithTag("name").performTextInput("rccun")
        rule.onNodeWithTag("signUpFAB").performClick()
    }

}



class AuthRepoImpl() : AuthRepository {

    override suspend fun signInWithGoogle(): Result<Unit> {
        return Result.success(Unit)
    }

    override suspend fun signUp(user: UserModel): CustomResult<Unit> {
        return CustomResult.Success(Unit)
    }

    override suspend fun signIn(
        email: String,
        password: String
    ): CustomResult<Unit> {
        return CustomResult.Success(Unit)
    }

    override suspend fun sendOTP(email: String): CustomResult<Unit> {
        return CustomResult.Success(Unit)
    }

    override suspend fun checkOTP(otp: String): CustomResult<Unit> {
        return CustomResult.Success(Unit)
    }

    override suspend fun resetPassword(password: String): CustomResult<Unit> {
        return CustomResult.Success(Unit)
    }
}

class emailValidatorImpl(): EmailValidator {
    override fun execute(email: String): CustomResult<Unit>? {
        return CustomResult.Success(Unit)
    }
}
