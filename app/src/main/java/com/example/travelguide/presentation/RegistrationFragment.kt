package com.example.travelguide.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.travelguide.R
import com.example.travelguide.databinding.FragmentRegistrationBinding
import com.example.travelguide.utilits.replaceFragment
import com.example.travelguide.viewModel.RegistrationViewModel

class RegistrationFragment : Fragment() {
    private var _binding : FragmentRegistrationBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: RegistrationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentRegistrationBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(requireActivity()).get(RegistrationViewModel::class.java)


        binding.btRegistration.setOnClickListener {
            val name = binding.edInputName.text.toString()
            val surName = binding.edInputSurname.text.toString()
            if (name.isEmpty() && surName.isEmpty()){
                Toast.makeText(context, getText(R.string.reg_toast), Toast.LENGTH_SHORT).show()
            } else{
                viewModel.registerUser(name, surName, 1)
                replaceFragment(HomeFragment())
            }
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).hideBottomNavigationView()
    }
}