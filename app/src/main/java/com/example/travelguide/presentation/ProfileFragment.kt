package com.example.travelguide.presentation

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.test.core.app.ApplicationProvider
import com.example.travelguide.R
import com.example.travelguide.databinding.FragmentProfileBinding
import com.example.travelguide.utilits.PERMISSION_STORAGE
import com.example.travelguide.utilits.PermissionUtils
import com.example.travelguide.utilits.REQUEST_READ_EXTERNAL_STORAGE
import com.example.travelguide.utilits.REQUEST_SELECT_IMAGE
import com.example.travelguide.viewModel.RegistrationViewModel
import com.soundcloud.android.crop.Crop
import java.io.*


class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: RegistrationViewModel


    @SuppressLint("ObsoleteSdkInt")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(RegistrationViewModel::class.java)

        val textName = viewModel.sharedPreferences.getString("name", "null")
        binding.tvProfileName.setText(textName)

        val textSurname = viewModel.sharedPreferences.getString("surname", "null")
        binding.tvProfileSurname.setText(textSurname)

        loadImageFromLocal()

        binding.iconProfile.setOnClickListener {
            if (PermissionUtils.hasPermissions(requireContext())) return@setOnClickListener;
            PermissionUtils.requestPermissions(requireContext() as Activity, PERMISSION_STORAGE);
            requestGalleryPermission()
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).showBottomNavigationView()
    }

    private fun requestGalleryPermission() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                REQUEST_READ_EXTERNAL_STORAGE
            )
        } else {
            openGallery()
        }
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, REQUEST_SELECT_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_SELECT_IMAGE && resultCode == Activity.RESULT_OK && data != null) {
            val selectedImageUri = data.data

            if (selectedImageUri != null) {
                Crop.of(selectedImageUri, Uri.fromFile(File(requireContext().cacheDir, "cropped")))
                    .asSquare()
                    .start(requireActivity())
                saveImageLocally(selectedImageUri)

                loadImageFromLocal()

            }
        }
    }


    private fun saveImageLocally(imageUri: Uri){
        val inputStream = requireContext().contentResolver.openInputStream(imageUri)
        val outputStream = FileOutputStream(getImageFile())
        inputStream?.copyTo(outputStream)
        inputStream?.close()
        outputStream.close()
    }

    private fun loadImageFromLocal() {
        val imageFile = getImageFile()
        if (imageFile.exists()) {
            val bitmap = BitmapFactory.decodeFile(imageFile.absolutePath)
            binding.iconProfile.setImageBitmap(bitmap)
        } else{
            binding.iconProfile.setImageResource(com.example.travelguide.R.drawable.user)
        }
    }

    private fun getImageFile(): File {
        val directory = requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File(directory, "profile_image.jpg")
    }
}