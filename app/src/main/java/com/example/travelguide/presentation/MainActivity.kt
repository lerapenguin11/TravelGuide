package com.example.travelguide.presentation

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.travelguide.R
import com.example.travelguide.databinding.ActivityMainBinding
import com.example.travelguide.utilits.APP_ACTIVITY
import com.example.travelguide.utilits.REQUEST_READ_EXTERNAL_STORAGE
import com.example.travelguide.utilits.replaceFragment
import com.example.travelguide.utilits.setStatusBarGradiant
import com.example.travelguide.viewModel.RegistrationViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: RegistrationViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        APP_ACTIVITY = this
        setStatusBarGradiant(this)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(RegistrationViewModel::class.java)

        val code = viewModel.codeSher.getInt("code", 0)
        if (code == 1){
            replaceFragment(HomeFragment())
            binding.bottomNavigationView.setOnItemSelectedListener {

                when(it.itemId){

                    R.id.home -> replaceFragment(HomeFragment())
                    R.id.all -> replaceFragment(FavoriteFragment())
                    R.id.profile -> replaceFragment(ProfileFragment())

                    else -> hideBottomNavigationView()
                }
                true
            }
        } else{
            replaceFragment(RegistrationFragment())
        }
    }



    fun hideBottomNavigationView() {
        binding.bottomNavigationView.visibility = View.GONE
    }

    fun showBottomNavigationView() {
        binding.bottomNavigationView.visibility = View.VISIBLE
    }
}