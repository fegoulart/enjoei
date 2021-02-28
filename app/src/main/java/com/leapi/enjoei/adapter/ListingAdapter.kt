package com.leapi.enjoei.adapter

import android.os.Bundle
import android.text.Layout
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.leapi.enjoei.databinding.ListingAdCellBinding
import com.leapi.enjoei.model.SearchEdge
import com.leapi.enjoei.view.ListingAdClickListener

class ListingAdapter(
    private val adsList: ArrayList<SearchEdge>,
    private val itemClickListener: ListingAdClickListener
) : RecyclerView.Adapter<ListingAdapter.ListingAdViewHolder>() {

    fun updateAds(newAdsList: List<SearchEdge>) {
        adsList.clear()
        adsList.addAll(newAdsList)
        notifyDataSetChanged()
    }

    class ListingAdViewHolder(
        private val itemBinding: ListingAdCellBinding,
        private val itemClickListener: ListingAdClickListener
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(adItem: SearchEdge) {
            itemBinding.titleTextView.text = adItem.node.title.name

            itemBinding.rootConstraintLayout.setOnClickListener {
                itemClickListener.onAdItemClicked(adItem)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListingAdViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = ListingAdCellBinding.inflate(inflater, parent, false)
        return ListingAdViewHolder(view, itemClickListener)
    }

    override fun onBindViewHolder(holder: ListingAdViewHolder, position: Int) {
        val adItem: SearchEdge = adsList[position]
        holder.bind(adItem)
    }

    override fun getItemCount(): Int {
        return adsList.size
    }
}