package com.example.mycleanarchitecture.framework.network.model

import com.google.gson.annotations.SerializedName

data class SecondStage (@SerializedName("payloads") var payloads: List<Payload>?)