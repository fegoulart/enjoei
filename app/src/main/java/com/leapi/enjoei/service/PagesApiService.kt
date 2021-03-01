package com.leapi.enjoei.service

import com.leapi.enjoei.di.service.DaggerPagesApiComponent
import com.leapi.enjoei.model.AdditionalAdData
import javax.inject.Inject

class PagesApiService {

    @Inject
    lateinit var api: PagesApi

    init {
        DaggerPagesApiComponent.create().inject(this)
    }

    suspend fun getAdDetail(id: Long): AdditionalAdData {
        return api.getAdDetail(id)
    }
}