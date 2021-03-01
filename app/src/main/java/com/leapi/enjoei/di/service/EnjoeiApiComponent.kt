package com.leapi.enjoei.di.service

import com.leapi.enjoei.service.EnjoeiApiService
import dagger.Component

@Component(modules=[EnjoeiApiModule::class])
interface EnjoeiApiComponent {
    fun inject(service: EnjoeiApiService)
}