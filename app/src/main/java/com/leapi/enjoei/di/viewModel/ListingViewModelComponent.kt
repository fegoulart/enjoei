package com.leapi.enjoei.di.viewModel

import com.leapi.enjoei.di.AppModule
import com.leapi.enjoei.di.service.SearchApiModule
import com.leapi.enjoei.service.SearchApiService
import com.leapi.enjoei.viewModel.ListingViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [SearchApiModule::class, AppModule::class])
interface ListingViewModelComponent {
    fun inject(viewModel: ListingViewModel)
}