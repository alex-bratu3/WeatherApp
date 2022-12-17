package com.example.funkyweather.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.signature.ObjectKey
import com.example.funkyweather.R
import com.example.funkyweather.databinding.FragmentWeatherListItemBinding
import com.example.funkyweather.network.Feature
import com.example.funkyweather.utils.PICS_URL
import com.example.funkyweather.utils.getDurationBetweenTwoDates

class WeatherEntryRecyclerViewAdapter(
    private val values: List<Feature>
) : RecyclerView.Adapter<WeatherEntryRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            FragmentWeatherListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]

        Glide.with(holder.itemView.context)
            .load(PICS_URL)
            .signature(ObjectKey(position))
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(R.drawable.ic_placeholder)
            .into(holder.image)

        with(item.properties) {
            holder.eventName.text = event
            holder.senderName.text = senderName
            holder.duration.text = holder.duration.context.getString(
                R.string.duration,
                effective.toString(),
                ends,
                getDurationBetweenTwoDates(effective, ends)
            )
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentWeatherListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val image: ImageView = binding.itemImage
        val eventName: TextView = binding.itemEventName
        val senderName: TextView = binding.itemSenderName
        val duration: TextView = binding.itemDuration
    }
}