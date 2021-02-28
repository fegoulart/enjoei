package com.leapi.enjoei.di.adapter

import com.leapi.enjoei.adapter.ListingAdapter
import dagger.Component

@Component(modules=[ListingAdapterModule::class])
interface ListingAdapterComponent {
    fun inject(adapter: ListingAdapter)
}