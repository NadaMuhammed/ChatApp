package com.example.chatapp.ui.chat

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.example.chatapp.Constants
import com.example.chatapp.R
import com.example.chatapp.base.BaseActivity
import com.example.chatapp.data.model.Room
import com.example.chatapp.databinding.ActivityChatBinding

class ChatActivity : BaseActivity<ChatViewModel,ActivityChatBinding>() {

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val room = intent.getSerializableExtra(Constants.ROOM, Room::class.java)
    }

    override fun getLayoutId(): Int = R.layout.activity_chat

    override fun initViewModel(): ChatViewModel = ViewModelProvider(this)[ChatViewModel::class.java]
}