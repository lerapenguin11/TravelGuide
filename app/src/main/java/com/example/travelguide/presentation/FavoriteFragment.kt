package com.example.travelguide.presentation

import android.app.Dialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        favoriteViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application = requireActivity().application)
        ).get(HomeViewModel::class.java)

        favoriteViewModel.favoriteAttractions.observe(viewLifecycleOwner, Observer { sightseeingList ->
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

    override fun detailsList(attDetailsList: AttractionModel) {
        val dialog = Dialog(requireContext(), android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(com.example.travelguide.R.layout.full_screen_details)
        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        dialog.window?.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val icon : ImageView = dialog.findViewById(com.example.travelguide.R.id.icon_place_details)
        val nameAttraction : TextView = dialog.findViewById(com.example.travelguide.R.id.tv_name_location_details)
        val locationAttach : TextView = dialog.findViewById(com.example.travelguide.R.id.tv_place_locatin_details)
        val tag : TextView = dialog.findViewById(com.example.travelguide.R.id.tv_continent)
        val desc : TextView = dialog.findViewById(com.example.travelguide.R.id.tv_desc_details)

        val addFav : ConstraintLayout = dialog.findViewById(com.example.travelguide.R.id.bt_add_fav_detail)
        val btArrow : ImageView = dialog.findViewById(com.example.travelguide.R.id.imageView)

        Glide.with(requireContext()).load(attDetailsList.icon).into(icon)

        nameAttraction.setText(attDetailsList.nameAttraction)
        locationAttach.setText(attDetailsList.locationAttach)
        tag.setText(attDetailsList.tag)
        desc.setText(attDetailsList.description)

        addFav.setOnClickListener {
            favoriteViewModel.allSightseeing.observe(viewLifecycleOwner, Observer {
                if (attDetailsList.isFavorite == false){
                    favoriteViewModel.insert(attDetailsList)
                    favoriteViewModel.update(attDetailsList)
                    attDetailsList.isFavorite = true
                }
            })
        }

        dialog.show()

        btArrow.setOnClickListener {
            dialog.cancel()
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).showBottomNavigationView()
    }
}