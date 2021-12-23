package com.missingcontroller.parttimer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.regex.Pattern

class CreateAccountViewModel : ViewModel() {
    private var viewModelJob = Job()
    private val emailRegex =
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}\\@[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})+"
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    var fName: String = ""
        set(value) {
            field = value.trim()
            validateForm()
        }

    var lName: String = ""
        set(value) {
            field = value.trim()
            validateForm()
        }

    var email: String = ""
        set(value) {
            field = value.trim()
            validateForm()
        }

    var password: String = ""
        set(value) {
            field = value.trim()
            validateForm()
        }

    var passwordConfirm: String = ""
        set(value) {
            field = value.trim()
            validateForm()
        }

    private val _onCreateClicked = MutableLiveData<Boolean>()
    val onCreateClicked: LiveData<Boolean>
        get() = _onCreateClicked

    private val _isValid = MutableLiveData<Boolean>()
    val isValid: LiveData<Boolean>
        get() = _isValid

    private val _userDetails = MutableLiveData<CreateAccountResponse>()
    val userDetails: LiveData<CreateAccountResponse>
        get() = _userDetails

    fun onCreateClick(){
        _onCreateClicked.value = true
    }

    private fun validateForm() {
        _isValid.value = false
        val pattern = Pattern.compile(emailRegex)

        if (email.isNotBlank()
            && pattern.matcher(email).matches()
            && password.isNotBlank()
            && password.length > 4
            && password == passwordConfirm
        ) {
            _isValid.value = true
        }
    }

    fun registration() {
        val userAccount = CreateAccount(
            "",
            fName,
            lName,
            "",
            email.lowercase(),
            password,
            true
        )
        coroutineScope.launch {
            val gson = Gson()
            println("Respons: ${gson.toJson(userAccount)}")
            val deferredlogIn = LoginApi.retrofitService.submitCreateAccount(userAccount)
            try {
                val result = deferredlogIn.await()
                println("Result: $result")
                CredentialManager.saveToken(result.id)
                _userDetails.value = result
            } catch (e: Exception) {
                println("Login Exception: $e")
            }
        }
    }
}