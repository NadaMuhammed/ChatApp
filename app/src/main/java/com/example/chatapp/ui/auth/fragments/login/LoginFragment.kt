package com.example.chatapp.ui.auth.fragments.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.chatapp.R
import com.example.chatapp.databinding.FragmentLoginBinding
import com.example.chatapp.ui.auth.MainActivity

class LoginFragment : Fragment() {
    lateinit var binding: FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.navigateToRegisterBtn.setOnClickListener {
            navigateToRegister()
        }
        binding.loginBtn.setOnClickListener {
            navigateToHome()
        }
    }

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
