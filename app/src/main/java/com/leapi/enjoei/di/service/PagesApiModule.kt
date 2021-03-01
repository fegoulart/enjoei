package com.leapi.enjoei.di.service

import com.leapi.enjoei.BuildConfig
import com.leapi.enjoei.service.PagesApi
import com.leapi.enjoei.service.PagesApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
open class PagesApiModule {

    private val BASE_URL = BuildConfig.PAGES_URL

    @Provides
    fun providePagesApi(): PagesApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PagesApi::class.java)
    }

    @Provides
    open fun providePagesApiService(): PagesApiService {
        return PagesApiService()
    }

}