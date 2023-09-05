package com.example.travelguide.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.travelguide.R
import com.example.travelguide.databinding.ActivityMainBinding
import com.example.travelguide.utilits.APP_ACTIVITY
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

        if (viewModel.sharedPreferences != null){
            binding.bottomNavigationView.visibility = View.VISIBLE
            replaceFragment(HomeFragment())
            binding.bottomNavigationView.setOnItemSelectedListener {

                when(it.itemId){

                    R.id.home -> replaceFragment(HomeFragment())
                    R.id.all -> replaceFragment(FavoriteFragment())
                    R.id.profile -> replaceFragment(ProfileFragment())
                }
                true
            }
        } else{
            replaceFragment(RegistrationFragment())
            binding.bottomNavigationView.visibility = View.INVISIBLE
        }
    }
}