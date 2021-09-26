package com.missingcontroller.parttimer;

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.regex.Pattern

class LoginViewModel : ViewModel() {
    private val emailRegex =
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}\\@[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})+"

    var userName: String = com.missingcontroller.parttimer.BuildConfig.DEMO_USER
        set(value) {
            field = value.trim()
            validateForm()
        }

    var password: String = com.missingcontroller.parttimer.BuildConfig.DEMO_PWD
        set(value) {
            field = value
            validateForm()
        }

    private val _isValid = MutableLiveData<Boolean>()
    val isValid: LiveData<Boolean>
        get() = _isValid

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
}
