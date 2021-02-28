package com.leapi.enjoei.di.view

import com.leapi.enjoei.di.adapter.ListingAdapterModule
import com.leapi.enjoei.view.ListingFragment
import dagger.Component

@Component(modules = [ListingAdapterModule::class])
interface ListingFragmentComponent {
    fun inject(fragment: ListingFragment)
}