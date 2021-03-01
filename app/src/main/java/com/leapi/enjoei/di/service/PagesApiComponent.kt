package com.leapi.enjoei.di.service

import com.leapi.enjoei.service.PagesApiService
import dagger.Component

@Component(modules=[PagesApiModule::class])
interface PagesApiComponent {
    fun inject(service: PagesApiService)
}