package com.example.mycleanarchitecture.framework.network.model

import com.google.gson.annotations.SerializedName

data class FirstStage(@SerializedName("cores") val cores: List<Core>?)