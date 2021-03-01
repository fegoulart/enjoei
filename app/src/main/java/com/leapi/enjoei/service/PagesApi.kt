package com.leapi.enjoei.service

import com.leapi.enjoei.model.AdditionalAdData
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface PagesApi {
    @Headers("Content-Type:application/json; charset=UTF-8")
    @GET("products/{id}/v2.json")
    suspend fun getAdDetail(@Path("id") id: Long): AdditionalAdData
}