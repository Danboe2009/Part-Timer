package com.missingcontroller.parttimer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.regex.Pattern

class LoginViewModel : ViewModel() {
    private var viewModelJob = Job()
    private val emailRegex =
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}\\@[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})+"
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    var userName: String = BuildConfig.DEMO_USER
        set(value) {
            field = value.trim()
            validateForm()
        }

    var password: String = BuildConfig.DEMO_PWD
        set(value) {
            field = value.trim()
            validateForm()
        }

    private val _isValid = MutableLiveData<Boolean>()
    val isValid: LiveData<Boolean>
        get() = _isValid

    private val _onSubmitClick = MutableLiveData<Boolean>()
    val onSubmitClick: LiveData<Boolean>
        get() = _onSubmitClick

    private val _onCreateClick = MutableLiveData<Boolean>()
    val onCreateClick: LiveData<Boolean>
        get() = _onCreateClick

    private val _userDetails = MutableLiveData<LoginResponse>()
    val userDetails: MutableLiveData<LoginResponse>
        get() = _userDetails

    private fun validateForm() {
        _isValid.value = false
        val pattern = Pattern.compile(emailRegex)

        if (userName.isNotBlank()
            && pattern.matcher(userName).matches()
            && password.isNotBlank()
            && password.length > 4
        ) {
            _isValid.value = true
        }
    }

    fun onSubmitClicked() {
        _onSubmitClick.value = true
    }

    fun onCreateClicked(){
        _onCreateClick.value = true
    }

    fun login() {
        val userAccount = UserAccount(userName.lowercase(), password)
        coroutineScope.launch {
            val deferredlogIn = LoginApi.retrofitService.submitLogIn(userAccount)
            try {
                val result = deferredlogIn.await()
                println("Result: $result")
                CredentialManager.saveToken(result.id)
                CredentialManager.saveUserId(result.userId)
                _userDetails.value = result
            } catch (e: Exception) {
                println("Login Exception: $e")
            }
        }
    }
}
