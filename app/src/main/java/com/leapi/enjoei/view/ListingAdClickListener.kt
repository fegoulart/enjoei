package com.leapi.enjoei.view

import com.leapi.enjoei.model.SearchEdge

interface ListingAdClickListener {
    fun onAdItemClicked(adItem: SearchEdge)
}