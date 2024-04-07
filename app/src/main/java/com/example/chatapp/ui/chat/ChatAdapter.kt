package com.example.chatapp.ui.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.Constants
import com.example.chatapp.R
import com.example.chatapp.data.model.Message
import com.example.chatapp.data.model.UserProvider
import com.example.chatapp.databinding.ReceivedMessageBinding
import com.example.chatapp.databinding.SentMessageBinding

class ChatAdapter(var messages: List<Message>) :
    RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {
    class ChatViewHolder(var binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        return if (viewType == Constants.SENT_MESSAGE_VIEW_TYPE){
            ChatViewHolder(DataBindingUtil.inflate<SentMessageBinding>(LayoutInflater.from(parent.context), R.layout.sent_message, parent, false))
        } else {
            ChatViewHolder(DataBindingUtil.inflate<ReceivedMessageBinding>(LayoutInflater.from(parent.context), R.layout.received_message, parent, false))
        }
    }

    override fun getItemViewType(position: Int): Int {
        val message = messages[position]
        return if (message.id == UserProvider.user!!.id) {
            Constants.RECEIVED_MESSAGE_VIEW_TYPE
        } else {
            Constants.SENT_MESSAGE_VIEW_TYPE
        }
    }

    override fun getItemCount(): Int = messages.size

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        if (holder.binding is ReceivedMessageBinding) {
            (holder.binding as ReceivedMessageBinding).message = messages[position]
        } else {
            (holder.binding as SentMessageBinding).message = messages[position]
        }
    }

    fun updateMessage(messages: List<Message>){
        this.messages = messages
        notifyDataSetChanged()
    }
}