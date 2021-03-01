package com.leapi.enjoei.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
data class BasicAdData(
    val brand: String,
    val adId: Long,
    val storeAvatar: String,
    val storeName: String,
    val adTitle: String,
    val fallbackPrice: Long,
    val storeId: String
)

@Keep
@Parcelize
data class AdditionalAdData(
    val id: Long,
    val title: String,
    val description: String,
    val used: Boolean,
    val brand: ProductBrand,
    val photos: List<String>,
    val seller_id: Long
): Parcelable

@Keep
@Parcelize
data class ProductBrand(
    val name: String
): Parcelable