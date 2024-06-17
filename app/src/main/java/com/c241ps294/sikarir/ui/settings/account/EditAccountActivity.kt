package com.c241ps294.sikarir.ui.settings.account

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.c241ps294.sikarir.databinding.ActivityEditAccountBinding
import com.c241ps294.sikarir.ui.authentication.viewmodel.AuthenticationViewModel
import com.c241ps294.sikarir.ui.authentication.viewmodel.AuthenticationViewModelFactory
import com.c241ps294.sikarir.ui.settings.SettingsActivity
import com.c241ps294.sikarir.ui.settings.viewmodel.EditAccountViewModel
import com.c241ps294.sikarir.ui.settings.viewmodel.EditAccountViewModelFactory
import kotlinx.coroutines.launch

class EditAccountActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditAccountBinding
    private val editAccountViewModel: EditAccountViewModel by viewModels {
        EditAccountViewModelFactory.getInstance(this)
    }
    private val authenticationViewModel by viewModels<AuthenticationViewModel> {
        AuthenticationViewModelFactory.getInstance(context = this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAction()
    }

    private fun setupAction() {
        binding.editAccountButton.setOnClickListener {
            val username = binding.inputUsernameEdit.text.toString()
            val name = binding.inputNameEdit.text.toString()
            val email = binding.inputEmailEdit.text.toString()
            val password = binding.inputPasswordEdit.text.toString()

            if (username.isNotEmpty() && name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                lifecycleScope.launch {
                    editAccountViewModel.editAccount(username, name, email, password)
                }
            } else {
                Toast.makeText(this, "Field tidak boleh kosong", Toast.LENGTH_SHORT).show()
            }
        }

        editAccountViewModel.editAccount.observe(this) { editAccountResponse ->
            if (editAccountResponse != null) {
                Toast.makeText(this, "Akun berhasil diubah", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Gagal mengubah akun", Toast.LENGTH_SHORT).show()
            }
        }

        editAccountViewModel.isLoading.observe(this) { isLoading ->
            if (isLoading) {
                showLoading(true)
            } else {
                showLoading(false)
            }
        }

        authenticationViewModel.getSession().observe(this) {
            Glide.with(this).load(it.photoUrl).into(binding.ivAvatarAccount)
            binding.inputUsernameEdit.setText(it.username)
            binding.inputEmailEdit.setText(it.email)
            binding.inputNameEdit.setText(it.name)
            binding.inputPasswordEdit.setText(it.password)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

}