package com.example.chatapp.ui.chat

import android.os.Build
import android.os.Build.VERSION_CODES.TIRAMISU
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.example.chatapp.Constants
import com.example.chatapp.R
import com.example.chatapp.base.BaseActivity
import com.example.chatapp.data.model.Room
import com.example.chatapp.databinding.ActivityChatBinding

class ChatActivity : BaseActivity<ChatViewModel,ActivityChatBinding>() {
    lateinit var adapter: ChatAdapter
    lateinit var room: Room
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        room = if(Build.VERSION.SDK_INT < TIRAMISU){
            intent.getSerializableExtra(Constants.ROOM) as Room
        } else{
            intent.getSerializableExtra(Constants.ROOM, Room::class.java)!!
        }
        viewModel.room = room
        viewModel.getMessages()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        adapter = ChatAdapter(listOf())
        binding.messagesRv.adapter = adapter
    }

    override fun initObservers() {
        super.initObservers()
        viewModel.messagesLiveData.observe(this){
            adapter.updateMessage(it)
        }
    }

    override fun getLayoutId(): Int = R.layout.activity_chat

    override fun initViewModel(): ChatViewModel = ViewModelProvider(this)[ChatViewModel::class.java]
}