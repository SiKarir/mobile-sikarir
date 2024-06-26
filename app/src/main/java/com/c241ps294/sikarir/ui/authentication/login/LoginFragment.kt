package com.c241ps294.sikarir.ui.authentication.login

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import com.c241ps294.sikarir.R
import com.c241ps294.sikarir.databinding.FragmentLoginBinding
import com.c241ps294.sikarir.ui.authentication.register.RegisterFragment
import com.c241ps294.sikarir.ui.authentication.viewmodel.AuthenticationViewModel
import com.c241ps294.sikarir.ui.authentication.viewmodel.AuthenticationViewModelFactory
import com.c241ps294.sikarir.ui.home.MainActivity

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val authViewModel by viewModels<AuthenticationViewModel> {
        AuthenticationViewModelFactory.getInstance(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAction()
    }

    private fun setupAction() {
        binding.loginButton.setOnClickListener {
            val username = binding.inputUsernameLogin.text.toString()
            val password = binding.inputPasswordLogin.text.toString()
            if (TextUtils.isEmpty(username)) {
                binding.inputUsernameLogin.error = "Field must be filled"
            } else if (TextUtils.isEmpty(password)) {
                binding.inputPasswordLogin.error = "Field must be filled"
            } else if ((binding.inputPasswordLogin.error?.length ?: 0) > 0) {
                binding.inputPasswordLogin.requestFocus()
            } else {
                authViewModel.login(username, password)
            }
        }

        authViewModel.loginUser.observe(viewLifecycleOwner) {
            it?.let {
                if (!it.error) {
                    Toast.makeText(context, "Login successful", Toast.LENGTH_SHORT).show()
                }
            }
        }

        authViewModel.isLoading.observe(viewLifecycleOwner) {
            showLoading(it)
        }

        authViewModel.errorMessageLogin.observe(viewLifecycleOwner) {
            it?.let {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
        }

        authViewModel.getSession().observe(viewLifecycleOwner) {
            if (it.isLogin) {
                navigateToMainActivity()
            }
        }

        binding.registerButton.setOnClickListener {
            parentFragmentManager.commit {
                addToBackStack(null)
                replace(R.id.authentication_container, RegisterFragment(), RegisterFragment::class.java.simpleName)
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun navigateToMainActivity() {
        val intent = Intent(requireActivity(), MainActivity::class.java)
        startActivity(intent)
        requireActivity().finishAffinity()
    }
}
