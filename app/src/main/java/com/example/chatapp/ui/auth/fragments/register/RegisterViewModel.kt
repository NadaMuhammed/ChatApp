package com.example.chatapp.ui.auth.fragments.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.chatapp.base.BaseViewModel
import com.example.chatapp.data.model.ViewMessage
import com.example.chatapp.data.repositories.auth_repo.AuthRepo
import com.example.chatapp.data.repositories.auth_repo.AuthRepoImpl
import kotlinx.coroutines.launch

class RegisterViewModel : BaseViewModel() {
    val authRepo: AuthRepo = AuthRepoImpl()
    val emailLiveData: MutableLiveData<String> = MutableLiveData()
    val emailErrorLiveData: MutableLiveData<String?> = MutableLiveData()
    val passwordLivaData: MutableLiveData<String> = MutableLiveData()
    val passwordErrorLiveData: MutableLiveData<String?> = MutableLiveData()
    val usernameLivaData: MutableLiveData<String> = MutableLiveData()
    val usernameErrorLiveData: MutableLiveData<String?> = MutableLiveData()
    val events: MutableLiveData<RegisterEvents> = MutableLiveData()

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
        viewModelScope.launch {
            loadingLiveData.value = true
            try {
                authRepo.register(emailLiveData.value!!, usernameLivaData.value!!, passwordLivaData.value!!)
                loadingLiveData.value = false
                events.value = RegisterEvents.NavigateToLoginEvent
            } catch (e: Exception){
                loadingLiveData.value = false
                errorDialogLiveData.value = ViewMessage("Error", e.localizedMessage, null, null, null, null)
            }
        }
    }
}