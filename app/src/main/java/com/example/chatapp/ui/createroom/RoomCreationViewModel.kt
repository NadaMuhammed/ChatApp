package com.example.chatapp.ui.createroom

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.chatapp.base.BaseViewModel
import com.example.chatapp.data.model.ViewMessage
import com.example.chatapp.data.repositories.rooms_repo.RoomsRepo
import com.example.chatapp.data.repositories.rooms_repo.RoomsRepoImpl
import kotlinx.coroutines.launch

class RoomCreationViewModel: BaseViewModel() {
    val roomsRepo: RoomsRepo = RoomsRepoImpl()
    val titleLiveData: MutableLiveData<String> = MutableLiveData()
    val titleErrorLiveData: MutableLiveData<String?> = MutableLiveData()
    val categoryLivaData: MutableLiveData<String> = MutableLiveData()
    val categoryErrorLiveData: MutableLiveData<String?> = MutableLiveData()
    val descriptionLivaData: MutableLiveData<String> = MutableLiveData()
    val descriptionErrorLiveData: MutableLiveData<String?> = MutableLiveData()
    val events: MutableLiveData<RoomEvents> = MutableLiveData()

    fun create(){
        if (!isValid()){
            return
        }
        viewModelScope.launch {
            loadingLiveData.value = true
            try {
                roomsRepo.createRoom(titleLiveData.value!!, categoryLivaData.value!!, descriptionLivaData.value!!)
                loadingLiveData.value = false
                events.value = RoomEvents.NavigateToHome
            } catch (e: Exception){
                loadingLiveData.value = false
                errorDialogLiveData.value = ViewMessage("Error", e.localizedMessage, null, null, null)
            }
        }
    }

    private fun isValid(): Boolean {
        var isValid = true
        if (titleLiveData.value.isNullOrEmpty()) {
            isValid = false
            titleErrorLiveData.value = "Please Enter A Valid Email"
        } else {
            titleErrorLiveData.value = null
        }
        if (categoryLivaData.value.isNullOrEmpty()) {
            isValid = false
            categoryErrorLiveData.value = "Please Enter A Valid Password"
        } else {
            categoryErrorLiveData.value = null
        }
        if (descriptionLivaData.value.isNullOrEmpty()) {
            isValid = false
            descriptionErrorLiveData.value = "Please Enter A Valid Email"
        } else {
            descriptionErrorLiveData.value = null
        }
        return isValid
    }
}