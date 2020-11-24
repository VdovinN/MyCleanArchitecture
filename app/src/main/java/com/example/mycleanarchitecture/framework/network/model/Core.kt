package com.example.mycleanarchitecture.framework.network.model

import com.google.gson.annotations.SerializedName

data class Core (
    @SerializedName("core_serial") val coreSerial: String?,
    @SerializedName("flight") val flight: Int?,
    @SerializedName("block") val block: Int?,
    @SerializedName("reused") val reused: Boolean?,
    @SerializedName("land_success") val landSuccess: Boolean?,
    @SerializedName("landing_type") val landingType: String?,
    @SerializedName("landing_vehicle") val landingVehicle: String?
)