package com.example.travelguide.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.travelguide.R
import com.example.travelguide.business.model.bd.AttractionModel
import com.example.travelguide.databinding.FragmentFavoriteBinding
import com.example.travelguide.databinding.FragmentHomeBinding
import com.example.travelguide.presentation.adapter.AttractionAdapter
import com.example.travelguide.presentation.adapter.listener.FavoriteListener
import com.example.travelguide.viewModel.HomeViewModel

class FavoriteFragment : Fragment(), FavoriteListener {
    private var _binding : FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private lateinit var favoriteViewModel: HomeViewModel
    private lateinit var favoriteAdapter: AttractionAdapter
    private val attractionList = mutableListOf<AttractionModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        favoriteViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application = requireActivity().application)
        ).get(HomeViewModel::class.java)

        favoriteViewModel.allSightseeing.observe(viewLifecycleOwner, Observer { sightseeingList ->
            val favoriteSightseeingList = sightseeingList.filter { it.isFavorite }
            favoriteAdapter = AttractionAdapter(favoriteSightseeingList, this)
            binding.rvPlaceFav.adapter = favoriteAdapter
            val layoutManager =  GridLayoutManager(requireContext(), 2)
            binding.rvPlaceFav.layoutManager = layoutManager
        })

        println("FAVARITE_SIZE:" + favoriteViewModel.allSightseeing.value)

        return binding.root
    }

    override fun favoriteList(attFavoriteList: AttractionModel) {
        if (attFavoriteList.isFavorite){
            attFavoriteList.isFavorite = false
            favoriteViewModel.delete(attFavoriteList)
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).showBottomNavigationView()
    }
}