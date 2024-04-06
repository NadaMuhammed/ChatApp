package com.example.chatapp.ui.home.fragments.myrooms

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.chatapp.R
import com.example.chatapp.data.model.Room
import com.example.chatapp.databinding.RoomItemBinding

class MyRoomsAdapter(var rooms: List<Room>, val onRoomClick: (Room)->Unit): RecyclerView.Adapter<MyRoomsAdapter.RoomsViewHolder>() {
    class RoomsViewHolder(val binding: RoomItemBinding): ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomsViewHolder {
        return RoomsViewHolder(DataBindingUtil.inflate<RoomItemBinding>(LayoutInflater.from(parent.context), R.layout.room_item, parent, false))
    }

    override fun getItemCount(): Int = rooms.size

    override fun onBindViewHolder(holder: RoomsViewHolder, position: Int) {
        holder.binding.room = rooms[position]
        holder.binding.executePendingBindings()
        holder.itemView.setOnClickListener {
            onRoomClick.invoke(rooms[position])
        }
    }

    fun updateRoom(rooms: List<Room>){
        this.rooms = rooms
        notifyDataSetChanged()
    }
}