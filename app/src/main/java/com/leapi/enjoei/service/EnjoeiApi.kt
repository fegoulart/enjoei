package com.leapi.enjoei.service

import com.leapi.enjoei.model.AdPricingData
import com.leapi.enjoei.model.StoreData
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface EnjoeiApi {
    @Headers("Content-Type:application/json; charset=UTF-8")
    @GET("api/v5/products/{id}/pricing.json")
    suspend fun getAdPricing(@Path("id") id: Long): AdPricingData

    @Headers("Content-Type:application/json; charset=UTF-8")
    @GET("api/v3/stores/{id}/store_stats")
    suspend fun getStoreDate(@Path("id") id: String): StoreData


}