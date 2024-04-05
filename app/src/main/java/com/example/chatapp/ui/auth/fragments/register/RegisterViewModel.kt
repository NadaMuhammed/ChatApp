package com.example.chatapp.ui.auth.fragments.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chatapp.data.repositories.AuthRepo
import com.example.chatapp.data.repositories.AuthRepoImpl

class RegisterViewModel : ViewModel() {
    val authRepo: AuthRepo = AuthRepoImpl()
    val emailLiveData: MutableLiveData<String> = MutableLiveData()
    val emailErrorLiveData: MutableLiveData<String?> = MutableLiveData()
    val passwordLivaData: MutableLiveData<String> = MutableLiveData()
    val passwordErrorLiveData: MutableLiveData<String?> = MutableLiveData()
    val usernameLivaData: MutableLiveData<String> = MutableLiveData()
    val usernameErrorLiveData: MutableLiveData<String?> = MutableLiveData()

    fun validate(): Boolean {
        var isValid = true
        if (emailLiveData.value.isNullOrEmpty()) {
            isValid = false
            emailErrorLiveData.value = "Please Enter A Valid Email"
        } else {
            emailErrorLiveData.value = null
        }
        if (passwordLivaData.value.isNullOrEmpty()) {
            isValid = false
            passwordErrorLiveData.value = "Please Enter A Valid Password"
        } else {
            passwordErrorLiveData.value = null
        }
        if (usernameLivaData.value.isNullOrEmpty()) {
            isValid = false
            usernameErrorLiveData.value = "Please Enter A Valid Email"
        } else {
            usernameErrorLiveData.value = null
        }
        return isValid
    }

    fun register() {
        if (!validate()) return
        authRepo.register(emailLiveData.value!!, usernameLivaData.value!!, passwordLivaData.value!!)
    }
}