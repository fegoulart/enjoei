package com.leapi.enjoei.service

import com.leapi.enjoei.di.service.DaggerEnjoeiApiComponent
import com.leapi.enjoei.model.AdPricingData
import javax.inject.Inject

class EnjoeiApiService {

    @Inject
    lateinit var api: EnjoeiApi

    init {
        DaggerEnjoeiApiComponent.create().inject(this)
    }

    suspend fun getPricingInformation(id: Long): AdPricingData {
        return api.getAdPricing(id)
    }

}