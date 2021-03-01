package com.leapi.enjoei.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class SearchResult(
    val data: SearchData
) : Parcelable

@Keep
@Parcelize
data class SearchData(
    val search: SearchSeller
): Parcelable

@Keep
@Parcelize
data class SearchSeller(
    val products: SearchProducts
): Parcelable

@Keep
@Parcelize
data class SearchProducts(
    val total: Long,
    val edges: List<SearchEdge>
): Parcelable

@Keep
@Parcelize
data class SearchEdge(
    val node: SearchNode
) : Parcelable

@Keep
@Parcelize
data class SearchNode(
    val id: Long,
    val title: SearchTitle,
    val brand: SearchBrand,
    val price: SearchPrice,
    val size: SearchSize,
    val store: SearchStore
) : Parcelable

@Keep
@Parcelize
data class SearchTitle(
    val name: String
) : Parcelable

@Keep
@Parcelize
data class SearchBrand(
    val displayable_name: String
) : Parcelable

@Keep
@Parcelize
data class SearchPrice(
    val original: Long,
    val current: Long
) : Parcelable

@Keep
@Parcelize
data class SearchSize(
    val name: String,
    val slug: String
) : Parcelable

@Keep
@Parcelize
data class SearchStore(
    val path: String,
    val displayable: SearchDisplayableStore
) : Parcelable

@Keep
@Parcelize
data class SearchDisplayableStore(
    val name: String,
    val avatar: SearchStoreAvatar
) : Parcelable

@Keep
@Parcelize
data class SearchStoreAvatar(
    val image_public_id: String
) : Parcelable