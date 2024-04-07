package com.example.chatapp.ui.chat

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.chatapp.base.BaseViewModel
import com.example.chatapp.data.model.Message
import com.example.chatapp.data.model.Room
import com.example.chatapp.data.repositories.rooms_repo.RoomsRepo
import com.example.chatapp.data.repositories.rooms_repo.RoomsRepoImpl
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ChatViewModel : BaseViewModel() {
    lateinit var room: Room
    val messageLiveData: MutableLiveData<String> = MutableLiveData()
    val messagesLiveData: MutableLiveData<List<Message>> = MutableLiveData()
    val roomsRepo: RoomsRepo = RoomsRepoImpl()

    fun sendMessage() {
        viewModelScope.launch {
            try {
                roomsRepo.sendMessage(messageLiveData.value!!, room.id)
                messageLiveData.value = ""
            } catch (e:Exception){
                e.localizedMessage?.let { Log.e("ChatViewModel", it) }
            }
        }
    }

    fun getMessages(){
        viewModelScope.launch {
            roomsRepo.getMessages(room.id).collect{
                messagesLiveData.value = it
//                Log.e("ChatViewModel", "New Flow: $it")
            }
        }
    }
}