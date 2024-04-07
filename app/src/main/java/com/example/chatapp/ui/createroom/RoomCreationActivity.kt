package com.example.chatapp.ui.createroom

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import com.example.chatapp.R
import com.example.chatapp.base.BaseActivity
import com.example.chatapp.databinding.ActivityRoomCreationBinding

class RoomCreationActivity : BaseActivity<RoomCreationViewModel, ActivityRoomCreationBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        val categories = listOf(resources.getString(R.string.movies), resources.getString(R.string.sports), resources.getString(R.string.music))
        val categoriesAdapter = ArrayAdapter(this, R.layout.category_dropdown_item_layout, categories)
        binding.roomCategoryAtv.setAdapter(categoriesAdapter)
    }

    override fun initObservers() {
        super.initObservers()
        viewModel.events.observe(this) {
            when (it) {
                is RoomEvents.NavigateToHome -> {
                    finish()
                }
            }
        }
    }

    override fun getLayoutId(): Int = R.layout.activity_room_creation

    override fun initViewModel(): RoomCreationViewModel =
        ViewModelProvider(this)[RoomCreationViewModel::class.java]
}