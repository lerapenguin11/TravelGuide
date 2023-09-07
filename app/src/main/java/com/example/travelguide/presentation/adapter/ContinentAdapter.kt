package com.example.travelguide.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.travelguide.R
import com.example.travelguide.business.model.ContinentModel
import com.example.travelguide.presentation.adapter.listener.ContinentListener

class ContinentAdapter(private val continentList : List<ContinentModel>,
                       private val listener : ContinentListener) : RecyclerView.Adapter<ContinentAdapter.ContinentViewHolder>() {

    private var selectedItem = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContinentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_continent, parent, false)

        return ContinentViewHolder(view)
    }

    override fun getItemCount(): Int = continentList.size

    override fun onBindViewHolder(holder: ContinentViewHolder,
                                  @SuppressLint("RecyclerView") position: Int) {
        val continent : ContinentModel = continentList[position]

        holder.icon.setImageResource(R.drawable.icon_place)
        holder.nameContinent.setText(continent.nameContinent)

        if (selectedItem == continent.id) {
            holder.itemView.setBackgroundResource(R.drawable.bg_item_place_active)
            listener.continentList(continent)
        } else {
            holder.itemView.setBackgroundResource(R.drawable.bg_item_place_not_active)
        }

        holder.itemView.setOnClickListener {
            selectedItem = position
            selectedItem = continent.id
            notifyDataSetChanged()
            listener.continentList(continent)
        }
    }

    class ContinentViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val icon : ImageView = view.findViewById(R.id.icon_place)
        val nameContinent : TextView = view.findViewById(R.id.tv_continent)
    }
}