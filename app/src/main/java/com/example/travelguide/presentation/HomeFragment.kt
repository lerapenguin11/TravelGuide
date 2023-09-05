package com.example.travelguide.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travelguide.R
import com.example.travelguide.business.model.AttractionModel
import com.example.travelguide.business.model.ContinentModel
import com.example.travelguide.databinding.FragmentHomeBinding
import com.example.travelguide.presentation.adapter.AttractionAdapter
import com.example.travelguide.presentation.adapter.ContinentAdapter
import com.example.travelguide.presentation.adapter.listener.ContinentListener

class HomeFragment : Fragment(), ContinentListener {
    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var continentAdapter: ContinentAdapter
    private lateinit var attractionAdapter: AttractionAdapter
    private val continentList = mutableListOf<ContinentModel>()
    private val attractionList = mutableListOf<AttractionModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        continentAdapter = ContinentAdapter(continentList, this)
        binding.rvContinent.adapter = continentAdapter
        binding.rvContinent.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        attractionAdapter = AttractionAdapter(attractionList)
        binding.rvPlace.adapter = attractionAdapter
        binding.rvPlace.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        loadContinents()

        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun loadContinents() {
        continentList.add(
            ContinentModel(0, "Европа", listOf(
                AttractionModel("Эйфелева башня",
        "vn", 3), AttractionModel("Колизей", "", 4)
            ))
        )
        continentList.add(
            ContinentModel(1, "Азия", listOf(AttractionModel("Великая Китайская стена",
                "", 4))))

        continentAdapter.notifyDataSetChanged()
    }

    override fun continentList(continent: ContinentModel) {
        attractionList.clear()
        attractionList.addAll(continent.attractionModels)
        attractionAdapter.notifyDataSetChanged()
    }

}