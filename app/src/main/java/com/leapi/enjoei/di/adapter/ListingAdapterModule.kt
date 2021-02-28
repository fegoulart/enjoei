package com.leapi.enjoei.di.adapter

import com.leapi.enjoei.adapter.ListingAdapter
import com.leapi.enjoei.model.SearchEdge
import com.leapi.enjoei.view.ListingAdClickListener
import dagger.Module
import dagger.Provides

@Module
open class ListingAdapterModule(
    private val adsList: ArrayList<SearchEdge>,
    private val itemClickListener: ListingAdClickListener
) {

    @Provides
    fun provideListingAdapter(): ListingAdapter {
        return ListingAdapter(adsList, itemClickListener)
    }
}