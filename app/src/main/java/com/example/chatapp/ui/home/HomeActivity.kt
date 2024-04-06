package com.example.chatapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.chatapp.R
import com.example.chatapp.databinding.ActivityHomeBinding
import com.example.chatapp.ui.createroom.RoomCreationActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.example.chatapp.ui.home.adapter.RoomsViewPagerAdapter
import com.example.chatapp.ui.roomdetails.RoomDetailsActivity

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRoomsViewPager()
        binding.addRoomBtn.setOnClickListener { navigateToRoomCreation() }
    }

    private fun navigateToRoomCreation() {
        startActivity(Intent(this, RoomCreationActivity::class.java))
    }

    private fun initRoomsViewPager() {
        val adapter = RoomsViewPagerAdapter(this)
        binding.roomsViewPager.adapter = adapter
        TabLayoutMediator(binding.roomsTabLayout, binding.roomsViewPager) { tab, position ->
            val tabTitles = resources?.getStringArray(R.array.rooms_fragments_titles) ?: emptyArray()
            tab.text = tabTitles[position]
        }.attach()
    }
}
