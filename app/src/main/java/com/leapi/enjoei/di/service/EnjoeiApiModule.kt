package com.leapi.enjoei.di.service

import com.leapi.enjoei.BuildConfig
import com.leapi.enjoei.service.EnjoeiApi
import com.leapi.enjoei.service.EnjoeiApiService
import com.leapi.enjoei.service.PagesApi
import com.leapi.enjoei.service.PagesApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
open class EnjoeiApiModule {

    private val BASE_URL = BuildConfig.DEFAULT_URL

    @Provides
    fun provideEnjoeiApi(): EnjoeiApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(EnjoeiApi::class.java)
    }

    @Provides
    open fun provideEnjoeiApiService(): EnjoeiApiService {
        return EnjoeiApiService()
    }

}