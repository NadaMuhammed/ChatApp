package com.example.chatapp.ui.home.fragments.myrooms

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.chatapp.base.BaseViewModel
import com.example.chatapp.data.model.Room
import com.example.chatapp.data.model.ViewMessage
import com.example.chatapp.data.repositories.rooms_repo.RoomsRepo
import com.example.chatapp.data.repositories.rooms_repo.RoomsRepoImpl
import kotlinx.coroutines.launch

class MyRoomsViewModel: BaseViewModel() {
    val rooms: MutableLiveData<List<Room>> = MutableLiveData()
    val roomsRepo: RoomsRepo = RoomsRepoImpl()
    fun getRooms() {
        viewModelScope.launch {
            loadingLiveData.value = true
            try {
                rooms.value = roomsRepo.getRooms()
                loadingLiveData.value = false
            } catch (e: Exception) {
                loadingLiveData.value = false
                errorDialogLiveData.value =
                    ViewMessage("Error", e.localizedMessage, null, null, null)
            }
        }
    }
}