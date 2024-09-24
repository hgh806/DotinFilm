package com.borna.dotinfilm.sections.data.remote.response.section

import kotlinx.serialization.Serializable

@Serializable
data class FilmDetails(
    val ages: Int? = null,
    val bannerUrl: String? = null,
    val code: String? = null,
    val company: String? = null,
    val countries: List<Country>? = null,
    val description: String? = null,
    val discountPercent: Int? = null,
    val duration: Int? = null,
    val galleries: List<String>? = null,
    val genres: List<Genre>? = null,
    val id: Int? = null,
    val imageUrl: String? = null,
    val imdbCode: String? = null,
    val imdbRate: Double? = null,
    val likePercent: Int? = null,
    val mobileBannerUrl: String? = null,
    val multiProvider: Boolean? = null,
    val multiQuality: Boolean? = null,
    val name: String? = null,
    val nameEn: String? = null,
    val nameFa: String? = null,
    val numOfDisLikes: Int? = null,
    val numOfLikes: Int? = null,
    val postId: Int? = null,
    val price: Int? = null,
    val priceWithVat: Int? = null,
    val saleable: Boolean? = null,
    val summary: String? = null,
    val userPostInfo: UserPostInfo? = null,
    val vat: Int? = null,
    val yearsOfBroadcast: Int? = null
)