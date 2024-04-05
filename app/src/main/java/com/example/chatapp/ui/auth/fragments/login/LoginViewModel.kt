package com.example.chatapp.ui.auth.fragments.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.chatapp.base.BaseViewModel
import com.example.chatapp.data.model.ViewMessage
import com.example.chatapp.data.repositories.AuthRepo
import com.example.chatapp.data.repositories.AuthRepoImpl
import kotlinx.coroutines.launch

class LoginViewModel : BaseViewModel() {
    val auth: AuthRepo = AuthRepoImpl()
    val emailLiveData: MutableLiveData<String> = MutableLiveData()
    val emailErrorLiveData: MutableLiveData<String?> = MutableLiveData()
    val passwordLiveData: MutableLiveData<String> = MutableLiveData()
    val passwordErrorLiveData: MutableLiveData<String?> = MutableLiveData()
    val events: MutableLiveData<LoginEvents> = MutableLiveData()
    fun login() {
        if (!isValid()) {
            return
        }
        viewModelScope.launch {
            loadingLiveData.value = true
            try {
                auth.login(emailLiveData.value!!, passwordLiveData.value!!)
                loadingLiveData.value = false
                events.value = LoginEvents.NavigateToHome
            } catch (e: Exception){
                errorDialogLiveData.value = ViewMessage("Error", e.localizedMessage, null, null, null)
                loadingLiveData.value = false
            }
        }
    }

    fun isValid(): Boolean {
        var isValid = true
        if (emailLiveData.value.isNullOrEmpty()) {
            emailErrorLiveData.value = "Please Enter A Valid Email!"
            isValid = false
        } else {
            emailErrorLiveData.value = null
        }

        if (passwordLiveData.value.isNullOrEmpty()) {
            passwordErrorLiveData.value = "Please Enter A Valid Email!"
            isValid = false
        } else {
            passwordErrorLiveData.value = null
        }
        return isValid
    }
}