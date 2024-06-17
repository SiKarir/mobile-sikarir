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
    private lateinit var username: String
    private lateinit var name: String
    private lateinit var email: String
    private lateinit var password: String

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
            username = binding.inputUsernameRegister.text.toString()
            name = binding.inputNameRegister.text.toString()
            email = binding.inputEmailRegister.text.toString()
            password = binding.inputPasswordRegister.text.toString()

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
            }
        }

        authViewModel.registerUser.observe(viewLifecycleOwner) {
            it?.let {
                if (!it.error) {
                    Toast.makeText(this.context, "Register successful", Toast.LENGTH_SHORT).show()
                    parentFragmentManager.beginTransaction().apply {
                        replace(R.id.authentication_container, LoginFragment(), LoginFragment::class.java.simpleName)
                        commit()
                    }
                }
            }
        }

        authViewModel.errorMessageRegister.observe(viewLifecycleOwner) {
            it?.let {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
        }

        authViewModel.isLoading.observe(viewLifecycleOwner) {
            showLoading(it)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}