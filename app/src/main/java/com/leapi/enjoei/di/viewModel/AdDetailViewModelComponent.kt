package com.leapi.enjoei.di.viewModel

import com.leapi.enjoei.di.AppModule
import com.leapi.enjoei.di.service.PagesApiModule
import com.leapi.enjoei.viewModel.AdDetailViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules  = [PagesApiModule::class, AppModule::class])
interface AdDetailViewModelComponent {
    fun inject(viewModel: AdDetailViewModel)
}