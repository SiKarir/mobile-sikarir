package com.c241ps294.sikarir.ui.authentication.register

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.c241ps294.sikarir.R
import com.c241ps294.sikarir.databinding.FragmentRegisterBinding
import com.c241ps294.sikarir.ui.authentication.login.LoginFragment
import com.c241ps294.sikarir.ui.authentication.viewmodel.AuthenticationViewModel
import com.c241ps294.sikarir.ui.authentication.viewmodel.AuthenticationViewModelFactory

class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private val authViewModel by viewModels<AuthenticationViewModel> {
        AuthenticationViewModelFactory.getInstance(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        authViewModel.isLoading.observe(viewLifecycleOwner) {
            showLoading(it)
        }

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
            val username = binding.inputUsernameRegister.text.toString()
            val name = binding.inputNameRegister.text.toString()
            val email = binding.inputEmailRegister.text.toString()
            val password = binding.inputPasswordRegister.text.toString()

            if (TextUtils.isEmpty(email)) {
                binding.inputEmailRegister.error = "Field must be filled"
            } else if (TextUtils.isEmpty(password)) {
                binding.inputPasswordRegister.error = "Field must be filled"
            } else if (TextUtils.isEmpty(name)) {
                binding.inputNameRegister.error = "Field must be filled"
            }  else if (TextUtils.isEmpty(username)) {
                binding.inputUsernameRegister.error = "Field must be filled"
            } else if ((binding.inputPasswordRegister.error?.length ?: 0) > 0) {
                binding.inputPasswordRegister.requestFocus()
            } else {
                authViewModel.register(username, name, email, password)
                authViewModel.registerUser.observe(viewLifecycleOwner) {
                    if (!it.error) {
                        Toast.makeText(this.context, "Register successful", Toast.LENGTH_SHORT).show()
                        parentFragmentManager.beginTransaction().apply {
                            replace(R.id.authentication_container, LoginFragment(), LoginFragment::class.java.simpleName)
                            commit()
                        }
                    }
                    else {
                        Toast.makeText(this.context, "Register failed", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}