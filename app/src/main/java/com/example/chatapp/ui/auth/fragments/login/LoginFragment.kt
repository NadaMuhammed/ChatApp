package com.example.chatapp.ui.auth.fragments.login

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.chatapp.R
import com.example.chatapp.base.BaseFragment
import com.example.chatapp.databinding.FragmentLoginBinding
import com.example.chatapp.ui.auth.MainActivity

class LoginFragment : BaseFragment<LoginViewModel, FragmentLoginBinding>() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.navigateToRegisterBtn.setOnClickListener {
            navigateToRegister()
        }
    }

    override fun initObservers() {
        super.initObservers()
        viewModel.events.observe(viewLifecycleOwner){
            when(it){
                is LoginEvents.NavigateToHome -> {
                    navigateToHome()
                }
                else -> {}
            }
        }
    }
    override fun getLayoutId(): Int = R.layout.fragment_login

    override fun initViewModel(): LoginViewModel =
        ViewModelProvider(this)[LoginViewModel::class.java]

    private fun navigateToHome() {
        val action = LoginFragmentDirections
            .actionLoginFragmentToHomeFragment()
        findNavController().navigate(action)
    }

    private fun navigateToRegister() {
        if (activity == null) return
        (activity as MainActivity).navController
            .navigate(R.id.action_loginFragment_to_registerFragment)
    }
}
