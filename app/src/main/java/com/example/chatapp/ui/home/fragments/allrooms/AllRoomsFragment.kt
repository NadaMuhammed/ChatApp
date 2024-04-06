package com.example.chatapp.ui.home.fragments.allrooms

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.chatapp.Constants
import com.example.chatapp.R
import com.example.chatapp.base.BaseFragment
import com.example.chatapp.databinding.FragmentMyRoomsBinding
import com.example.chatapp.ui.chat.ChatActivity
import com.example.chatapp.ui.home.fragments.myrooms.MyRoomsAdapter
import com.example.chatapp.ui.home.fragments.myrooms.MyRoomsViewModel

class AllRoomsFragment : BaseFragment<MyRoomsViewModel, FragmentMyRoomsBinding>() {
    val adapter = MyRoomsAdapter(listOf()){
        val intent = Intent(activity, ChatActivity::class.java)
        intent.putExtra(Constants.ROOM, it)
        startActivity(intent)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.roomsRv.adapter = adapter
    }

    override fun initObservers() {
        super.initObservers()
        viewModel.rooms.observe(viewLifecycleOwner){
            adapter.updateRoom(it)
        }
    }
    override fun onResume() {
        super.onResume()
        viewModel.getRooms()
    }
    override fun getLayoutId(): Int = R.layout.fragment_my_rooms

    override fun initViewModel(): MyRoomsViewModel = ViewModelProvider(this).get(MyRoomsViewModel::class.java)

}