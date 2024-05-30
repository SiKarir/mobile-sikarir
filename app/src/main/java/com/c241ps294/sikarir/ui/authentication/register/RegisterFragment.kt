package com.c241ps294.sikarir.ui.authentication.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.c241ps294.sikarir.R
import com.c241ps294.sikarir.databinding.FragmentRegisterBinding
import com.c241ps294.sikarir.ui.authentication.login.LoginFragment

class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAction()
    }

    private fun setupAction() {
        binding.loginButton.setOnClickListener {
            parentFragmentManager.beginTransaction().apply {
                replace(R.id.authentication_container, LoginFragment(), LoginFragment::class.java.simpleName)
                commit()
            }
        }
        binding.registerButton.setOnClickListener {
            parentFragmentManager.beginTransaction().apply {
                replace(R.id.authentication_container, LoginFragment(), LoginFragment::class.java.simpleName)
                commit()
            }
        }
    }
}