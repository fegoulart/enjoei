package com.leapi.enjoei.viewModel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.leapi.enjoei.di.viewModel.DaggerListingViewModelComponent
import com.leapi.enjoei.model.SearchEdge
import com.leapi.enjoei.service.SearchApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import javax.inject.Inject

class ListingViewModel(application: Application) : BaseViewModel(application) {

    val listingAds = MutableLiveData<List<SearchEdge>>()
    val loading = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()
    private var injected = false

    @Inject
    lateinit var apiService: SearchApiService

    fun inject() {
        if (!injected) {
            DaggerListingViewModelComponent.builder()
                .build()
                .inject(this)
            injected = true
        }
    }

    fun getStarWarsProducts() {
        inject()
        CoroutineScope(Dispatchers.IO).launch {
            loading.postValue(true)
            val apiResponse = apiService.getFirstStarWarsProducts()
            withContext(Dispatchers.Main) {
                try {
                    if (apiResponse.data.search.products.edges.isNotEmpty()) {
                        listingAds.postValue(apiResponse.data.search.products.edges)
                    }
                } catch(e: HttpException) {
                    errorMessage.value = "HTTP Error"
                } catch (e: Throwable) {
                    errorMessage.value = "Network Error"
                }
                loading.postValue(false)
            }
        }
    }

}