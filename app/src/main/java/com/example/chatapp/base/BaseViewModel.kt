package com.example.chatapp.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chatapp.data.model.ViewMessage

open class BaseViewModel: ViewModel() {
    val loadingLiveData = MutableLiveData<Boolean>()
    val errorDialogLiveData = MutableLiveData<ViewMessage>()
}