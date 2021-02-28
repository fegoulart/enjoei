package com.leapi.enjoei.di.service

import com.leapi.enjoei.service.SearchApiService
import dagger.Component

@Component(modules = [SearchApiModule::class])
interface SearchApiComponent {
    fun inject(service: SearchApiService)
}