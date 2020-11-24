package com.example.mycleanarchitecture.framework.network.model

import com.google.gson.annotations.SerializedName

data class Reuse (
    @SerializedName("core") val core: Boolean?,
    @SerializedName("side_core1") val sideCore1: Boolean?,
    @SerializedName("side_core2") val sideCore2: Boolean?,
    @SerializedName("fairings") val fairings: Boolean?,
    @SerializedName("capsule") val capsule: Boolean?
)