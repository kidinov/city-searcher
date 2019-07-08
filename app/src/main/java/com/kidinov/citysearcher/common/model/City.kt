package com.kidinov.citysearcher.common.model

import com.google.gson.annotations.SerializedName

data class City(
    @SerializedName("_id")
    val id: Long,
    val name: String,
    val country: String,
    val coord: Coordinates
)

data class Coordinates(
    val lon: Double,
    val lat: Double
)