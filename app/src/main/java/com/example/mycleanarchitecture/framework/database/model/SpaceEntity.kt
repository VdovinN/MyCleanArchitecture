package com.example.mycleanarchitecture.framework.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "spaces")
class SpaceEntity(
    @PrimaryKey
    val flightNumber: Long,
    val missionName: String?,
    val details: String?,
    val launchDate: String?,
    val rocketName: String?,
    val payloadMass: Double?,
    val infoLink: String?,
    val youtubeVideoId: String?
)