package com.example.applaunch.repository

import com.google.gson.annotations.SerializedName
import java.net.IDN

data class WeatherResponse(
    @SerializedName("current")
    val current:CurrentReport?,
    )

data class CurrentReport(
    @SerializedName("temp")
    val temp:String?,
    @SerializedName("humidity")
    val humidity:String?,
    @SerializedName("wind_speed")
    val windSpeed:String?,
    @SerializedName("weather")
    val weatherDetail:List<WeatherDetail>?,

    )

data class WeatherDetail(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("main")
    val main:String?,
    @SerializedName("description")
    val description:String?,



)
