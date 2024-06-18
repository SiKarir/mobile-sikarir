package com.c241ps294.sikarir.ui.settings.account

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.c241ps294.sikarir.databinding.ActivityEditAccountBinding
import com.c241ps294.sikarir.ui.authentication.viewmodel.AuthenticationViewModel
import com.c241ps294.sikarir.ui.authentication.viewmodel.AuthenticationViewModelFactory
import com.c241ps294.sikarir.ui.settings.SettingsActivity
import com.c241ps294.sikarir.ui.settings.viewmodel.EditAccountViewModel
import com.c241ps294.sikarir.ui.settings.viewmodel.EditAccountViewModelFactory
import com.c241ps294.sikarir.utils.reduceFileImage
import com.c241ps294.sikarir.utils.uriToFile
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody

class EditAccountActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditAccountBinding
    private val editAccountViewModel: EditAccountViewModel by viewModels {
        EditAccountViewModelFactory.getInstance(this)
    }
    private val authenticationViewModel by viewModels<AuthenticationViewModel> {
        AuthenticationViewModelFactory.getInstance(context = this)
    }
    private var currentImageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAction()
    }

    private fun setupAction() {
        binding.btnEditImage.setOnClickListener{ startGallery() }

        binding.editAccountButton.setOnClickListener { editAccount() }

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

    private fun startGallery() {
        launcherGallery.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    private val launcherGallery = registerForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        if (uri != null) {
            currentImageUri = uri
            showImage()
        } else {
            Log.d("Photo Picker", "No media selected")
        }
    }

    private fun showImage() {
        currentImageUri?.let {
            Log.d("Image URI", "showImage: $it")
            binding.ivAvatarAccount.setImageURI(it)
        }
    }

    private fun editAccount() {
        val username = binding.inputUsernameEdit.text.toString()
        val name = binding.inputNameEdit.text.toString()
        val email = binding.inputEmailEdit.text.toString()
        val password = binding.inputPasswordEdit.text.toString()

        currentImageUri?.let { uri ->
            val imageFile = uriToFile(uri, this).reduceFileImage()
            Log.d("Image File", "showImage: ${imageFile.path}")
            val description = "Ini adalah deksripsi gambar"
            showLoading(true)

            val usernameRequest = username.toRequestBody("text/plain".toMediaType())
            val nameRequest = name.toRequestBody("text/plain".toMediaType())
            val emailRequest = email.toRequestBody("text/plain".toMediaType())
            val passwordRequest = password.toRequestBody("text/plain".toMediaType())
            val requestImageFile = imageFile.asRequestBody("image/jpeg".toMediaType())
            val multipartBody = MultipartBody.Part.createFormData(
                "photo",
                imageFile.name,
                requestImageFile
            )
            lifecycleScope.launch {
                editAccountViewModel.editAccount(usernameRequest,nameRequest,emailRequest,passwordRequest,multipartBody)
            }
        }
    }
}