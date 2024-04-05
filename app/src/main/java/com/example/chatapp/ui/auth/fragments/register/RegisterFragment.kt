package com.example.chatapp.ui.auth.fragments.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.chatapp.R
import com.example.chatapp.base.BaseFragment
import com.example.chatapp.databinding.FragmentRegisterBinding

class RegisterFragment : BaseFragment<RegisterViewModel, FragmentRegisterBinding>() {

    override fun getLayoutId(): Int = R.layout.fragment_register

    override fun initViewModel(): RegisterViewModel = ViewModelProvider(this)[RegisterViewModel::class.java]


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
    }
}
