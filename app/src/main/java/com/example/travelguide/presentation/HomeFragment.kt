package com.example.travelguide.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travelguide.business.model.bd.AttractionModel
import com.example.travelguide.business.model.ContinentModel
import com.example.travelguide.databinding.FragmentHomeBinding
import com.example.travelguide.presentation.adapter.AttractionAdapter
import com.example.travelguide.presentation.adapter.ContinentAdapter
import com.example.travelguide.presentation.adapter.listener.ContinentListener
import com.example.travelguide.presentation.adapter.listener.FavoriteListener
import com.example.travelguide.viewModel.HomeViewModel

class HomeFragment : Fragment(), ContinentListener, FavoriteListener {
    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var continentAdapter: ContinentAdapter
    private lateinit var attractionAdapter: AttractionAdapter
    private val continentList = mutableListOf<ContinentModel>()
    private val attractionList = mutableListOf<AttractionModel>()
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        homeViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application = requireActivity().application)
        ).get(HomeViewModel::class.java)



        continentAdapter = ContinentAdapter(continentList, this)
        binding.rvContinent.adapter = continentAdapter
        binding.rvContinent.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        attractionAdapter = AttractionAdapter(attractionList, this)
        binding.rvPlace.adapter = attractionAdapter
        val layoutManager =  GridLayoutManager(requireContext(), 2)
        binding.rvPlace.layoutManager = layoutManager

        loadContinents()

        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun loadContinents() {
        continentList.add(
            ContinentModel(0, "Европа", listOf(
                AttractionModel(0, "Эйфелева башня",
        "vn", 3f, "Эйфелева башня"), AttractionModel(1, "Колизей", "",
                    4f, "Европа")
            ))
        )
        continentList.add(
            ContinentModel(1, "Азия", listOf(
                AttractionModel(2, "Великая Китайская стена",
                "", 4f, "Европа")
            )))

        continentAdapter.notifyDataSetChanged()
    }

    override fun continentList(continent: ContinentModel) {
        attractionList.clear()
        attractionList.addAll(continent.attractionModels)
        //homeViewModel.getAll()

        attractionAdapter.notifyDataSetChanged()
    }

    override fun favoriteList(attFavoriteList: AttractionModel) {
        homeViewModel.getAll()
        attFavoriteList.isFavorite = true
        homeViewModel.update(attFavoriteList)
        homeViewModel.insert(attFavoriteList)
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).showBottomNavigationView()
    }
}