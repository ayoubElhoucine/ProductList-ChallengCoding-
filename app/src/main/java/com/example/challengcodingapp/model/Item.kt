package com.example.challengcodingapp.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Item(
    @Json(name = "product_id") val id: Int,
    @Json(name = "product_name") val name: String,
    @Json(name = "description") val description: String?,
    @Json(name = "price") val price: Double,
    @Json(name = "images_url") val image: ImageUrl?,
    @Json(name = "c_brand") val brand: Brand?,
    @Json(name = "is_productSet") val isProductSet: Boolean?,
    @Json(name = "is_special_brand") val isSpecialBrand: Boolean?
): Parcelable

@Parcelize
data class ImageUrl(
    @Json(name = "small") val small: String,
    @Json(name = "large") val large: String?
): Parcelable

@Parcelize
data class Brand (
    @Json(name = "id") val id: String,
    @Json(name = "name") val name: String,
): Parcelable