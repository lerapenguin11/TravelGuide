package com.example.travelguide.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.travelguide.R
import com.example.travelguide.business.model.AttractionModel

class AttractionAdapter(private val attractionModelList : List<AttractionModel>) : RecyclerView.Adapter<AttractionAdapter.AttractionViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttractionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_place, parent, false)

        return AttractionViewHolder(view)
    }

    override fun getItemCount(): Int = attractionModelList.size

    override fun onBindViewHolder(holder: AttractionViewHolder, position: Int) {
        val attraction : AttractionModel = attractionModelList[position]

        holder.nameAttraction.text = attraction.nameAttraction
        holder.locationAttach.text = attraction.locationAttach
        holder.rating.text = attraction.rating.toString()
    }

    class AttractionViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val nameAttraction : TextView = view.findViewById(R.id.tv_name_location)
        val locationAttach : TextView = view.findViewById(R.id.tv_place_locatin)
        val rating : TextView = view.findViewById(R.id.tv_rating)
    }
}