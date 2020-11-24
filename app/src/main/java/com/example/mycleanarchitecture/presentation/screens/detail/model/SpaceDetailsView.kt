package com.example.mycleanarchitecture.presentation.screens.detail.model

data class SpaceDetailsView(
    val flightNumber: Long,
    val missionName: String,
    val launchDate: String,
    val details: String,
    val rocketName: String,
    val payloadMass: String,
    val wikiLink: String,
    val youtubeVideoId: String
)