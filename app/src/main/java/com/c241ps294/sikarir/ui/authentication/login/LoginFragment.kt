package com.c241ps294.sikarir.ui.authentication.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.commit
import com.c241ps294.sikarir.R
import com.c241ps294.sikarir.databinding.FragmentLoginBinding
import com.c241ps294.sikarir.ui.authentication.register.RegisterFragment
import com.c241ps294.sikarir.ui.home.MainActivity

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding

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

            if (isValidCredentials(username, password)) {
                startActivity(Intent(requireActivity(), MainActivity::class.java))
                requireActivity().finish()
            } else {
                Toast.makeText(requireContext(), "Login gagal. Coba lagi.", Toast.LENGTH_SHORT).show()
            }
        }

        binding.registerButton.setOnClickListener {
            parentFragmentManager.commit {
                addToBackStack(null)
                replace(R.id.authentication_container, RegisterFragment(), RegisterFragment::class.java.simpleName)
            }
        }
    }

    private fun isValidCredentials(username: String, password: String): Boolean {
        return username == "username" && password == "password"
    }

}
