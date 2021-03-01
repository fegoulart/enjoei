package com.leapi.enjoei.viewModel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.leapi.enjoei.di.viewModel.DaggerAdDetailViewModelComponent
import com.leapi.enjoei.model.AdditionalAdData
import com.leapi.enjoei.model.BasicAdData
import com.leapi.enjoei.model.SearchEdge
import com.leapi.enjoei.service.PagesApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import javax.inject.Inject

class AdDetailViewModel(application: Application) : BaseViewModel(application) {

    val basicAdData = MutableLiveData<BasicAdData>()
    val additionalAdData = MutableLiveData<AdditionalAdData>()
    val loading = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()
    private var injected = false

    @Inject
    lateinit var pagesApiService: PagesApiService

    fun inject() {
        if (!injected) {
            DaggerAdDetailViewModelComponent.builder()
                .build()
                .inject(this)
            injected = true
        }
    }

    fun setBasicAdData(edgeFromListing: SearchEdge) {
        basicAdData.value =
            BasicAdData(
                edgeFromListing.node.brand.displayable_name,
                edgeFromListing.node.id,
                edgeFromListing.node.store.displayable.avatar.image_public_id,
                edgeFromListing.node.store.displayable.name,
                edgeFromListing.node.title.name,
                edgeFromListing.node.price.current,
                edgeFromListing.node.store.path
            )
    }

    fun getAdditionalData() {
        inject()
        basicAdData.value?.adId?.let {
            CoroutineScope(Dispatchers.IO).launch {
                loading.postValue(true)
                val apiResponse = pagesApiService.getAdDetail(it)
                withContext(Dispatchers.Main) {
                    try {
                        additionalAdData.postValue(apiResponse)
                    } catch (e: HttpException) {
                        errorMessage.value = "HTTP Error"
                    } catch (e: Throwable) {
                        errorMessage.value = "Network Error"
                    }
                    loading.postValue(false)
                }
            }
        }
    }


}