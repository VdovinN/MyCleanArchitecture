package com.example.mycleanarchitecture.domain

data class Space(
    val flightNumber: Long,
    val missionName: String,
    val details: String,
    val launchDate: String,
    val rocketName: String,
    val payloadMass: Double,
    val infoLink: String,
    val videoId: String
)