package com.example.travelguide.presentation

import android.R
import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
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
            ContinentModel(0, com.example.travelguide.R.string.cont_1, listOf(
                AttractionModel(0, com.example.travelguide.R.string.att_1,
                    com.example.travelguide.R.string.loc_1, 3f, "Эйфелева башня", com.example.travelguide.R.string.desc_3, com.example.travelguide.R.string.cont_1, "https://aikidzin.ru/wp-content/uploads/1/3/a/13a7a12dff1021a9cf1dd995c12f1474.jpeg"),
                AttractionModel(1, com.example.travelguide.R.string.att_2, com.example.travelguide.R.string.loc_2,
                    5f, "Европа", com.example.travelguide.R.string.desc_2, com.example.travelguide.R.string.cont_1, "https://potokmedia.ru/wp-content/uploads/2023/09/the-great-wall.jpg")
            ))
        )
        continentList.add(
            ContinentModel(1, com.example.travelguide.R.string.cont_2, listOf(
                AttractionModel(2, com.example.travelguide.R.string.att_3,
                    com.example.travelguide.R.string.loc_3, 1f, "Европа", com.example.travelguide.R.string.desc_4, com.example.travelguide.R.string.cont_2,
                            "https://mykaleidoscope.ru/x/uploads/posts/2022-10/1666358595_5-mykaleidoscope-ru-p-vodopad-viktoriya-krasivo-6.jpg")
            )))

        continentList.add(
            ContinentModel(2, com.example.travelguide.R.string.cont_3, listOf(
                AttractionModel(0, com.example.travelguide.R.string.att_4,
                    com.example.travelguide.R.string.cont_4, 5f, "Эйфелева башня", com.example.travelguide.R.string.desc_1, com.example.travelguide.R.string.cont_3, "https://img.ixbt.site/live/topics/preview/00/03/49/04/c78c1e148e.jpg")
                , AttractionModel(1, com.example.travelguide.R.string.att_5, com.example.travelguide.R.string.cont_5,
                    2f, "Европа", com.example.travelguide.R.string.desc_2, com.example.travelguide.R.string.cont_3, "https://www.putivodi.ru/upload/medialibrary/086/большой-барьерный-риф-австралия-3.jpg")
            ))
        )

        continentList.add(
            ContinentModel(3, com.example.travelguide.R.string.cont_4, listOf(
                AttractionModel(0, com.example.travelguide.R.string.att_3,
                    com.example.travelguide.R.string.loc_2, 3f, "Эйфелева башня", com.example.travelguide.R.string.desc_3, com.example.travelguide.R.string.cont_4, "https://potokmedia.ru/wp-content/uploads/2023/09/the-great-wall.jpg"),
                AttractionModel(1, com.example.travelguide.R.string.att_3, com.example.travelguide.R.string.loc_2,
                    4f, "Европа", com.example.travelguide.R.string.desc_2, com.example.travelguide.R.string.cont_4, "https://potokmedia.ru/wp-content/uploads/2023/09/the-great-wall.jpg")
            ))
        )

        continentList.add(
            ContinentModel(4, com.example.travelguide.R.string.cont_5, listOf(
                AttractionModel(0, com.example.travelguide.R.string.att_6,
                    com.example.travelguide.R.string.att_6, 3f, "Эйфелева башня", com.example.travelguide.R.string.desc_1, com.example.travelguide.R.string.cont_5, "https://potokmedia.ru/wp-content/uploads/2023/09/the-great-wall.jpg"),
                AttractionModel(1, com.example.travelguide.R.string.att_7, com.example.travelguide.R.string.cont_6,
                    5f, "Европа", com.example.travelguide.R.string.desc_1, com.example.travelguide.R.string.cont_6, "https://potokmedia.ru/wp-content/uploads/2023/09/the-great-wall.jpg")
            ))
        )

        continentAdapter.notifyDataSetChanged()
    }

    override fun continentList(continent: ContinentModel) {
        attractionList.clear()

        attractionList.addAll(continent.attractionModels)
        //homeViewModel.getAll()


        attractionAdapter.notifyDataSetChanged()
    }

    override fun favoriteList(attFavoriteList: AttractionModel) {

        homeViewModel.allSightseeing.observe(viewLifecycleOwner, Observer {
            if (attFavoriteList.isFavorite == false){
                homeViewModel.insert(attFavoriteList)
                homeViewModel.update(attFavoriteList)
                attFavoriteList.isFavorite = true
            }
        })
    }

    override fun detailsList(attDetailsList: AttractionModel) {
        val dialog = Dialog(requireContext(), R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen)
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
            homeViewModel.allSightseeing.observe(viewLifecycleOwner, Observer {
                if (attDetailsList.isFavorite == false){
                    homeViewModel.insert(attDetailsList)
                    homeViewModel.update(attDetailsList)
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