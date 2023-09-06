package com.example.travelguide.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.travelguide.R
import com.example.travelguide.business.model.bd.AttractionModel
import com.example.travelguide.presentation.adapter.listener.FavoriteListener

class AttractionAdapter(private val attractionModelList : List<AttractionModel>,
                        private val listener : FavoriteListener) : RecyclerView.Adapter<AttractionAdapter.AttractionViewHolder>() {

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

        holder.ratingBar.rating = attraction.rating
        holder.ratingBar.numStars = 5

        if (attraction.isFavorite){
            //listener.favoriteList(attraction)
            holder.ic_fav.setImageResource(R.drawable.ic_fav)
        } /*else{
            //listener.favoriteList(attraction)
            holder.ic_fav.setImageResource(R.drawable.ic_favorite)
        }*/
        holder.bt_fav.setOnClickListener {
            listener.favoriteList(attraction)
            holder.ic_fav.setImageResource(R.drawable.ic_fav)

            /*if (attraction.isFavorite){
                listener.favoriteList(attraction)
                holder.ic_fav.setImageResource(R.drawable.ic_fav)
            } else{
                //listener.favoriteList(attraction)
                holder.ic_fav.setImageResource(R.drawable.ic_favorite)
            }*/
            //listener.favoriteList(attraction)

        }
    }

    class AttractionViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val nameAttraction : TextView = view.findViewById(R.id.tv_name_location)
        val locationAttach : TextView = view.findViewById(R.id.tv_place_locatin)
        val rating : TextView = view.findViewById(R.id.tv_rating)
        val ratingBar : RatingBar = view.findViewById(R.id.ratingBar)
        val bt_fav : ConstraintLayout = view.findViewById(R.id.bt_add_fav)
        val ic_fav : ImageView = view.findViewById(R.id.ic_fav)
    }
}