package com.leapi.enjoei.service

import com.leapi.enjoei.model.SearchResult
import retrofit2.http.GET
import retrofit2.http.Headers

interface SearchApi {

    @Headers("Content-Type:application/json; charset=UTF-8")
    @GET("graphql-search-x?city=medianeira&experienced_seller=true&first=30&operation_name=searchProducts&query_id=c979e1f7f6478dabdf28c571789010d3&shipping_range=near_regions&state=pr&term=star-wars")
    suspend fun searchProducts(): SearchResult

}