package com.leapi.enjoei.di.service

import com.leapi.enjoei.BuildConfig
import com.leapi.enjoei.service.SearchApi
import com.leapi.enjoei.service.SearchApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
open class SearchApiModule {

    private val BASE_URL = BuildConfig.SEARCH_URL

    @Provides
    fun provideSearchApi(): SearchApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SearchApi::class.java)
    }

    @Provides
    open fun provideSearchApiService(): SearchApiService {
        return SearchApiService()
    }

}