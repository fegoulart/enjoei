package com.leapi.enjoei.service

import com.leapi.enjoei.di.service.DaggerSearchApiComponent
import com.leapi.enjoei.model.SearchResult
import javax.inject.Inject

class SearchApiService {

    @Inject
    lateinit var api: SearchApi

    init {
        DaggerSearchApiComponent.create().inject(this)
    }

    suspend fun getFirstStarWarsProducts(): SearchResult {
        return api.searchProducts()
    }


}