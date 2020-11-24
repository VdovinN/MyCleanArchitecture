package com.example.mycleanarchitecture.presentation.util.mapper

import com.example.mycleanarchitecture.domain.Space
import com.example.mycleanarchitecture.framework.database.model.SpaceEntity
import com.example.mycleanarchitecture.framework.network.model.SpaceResponse
import com.example.mycleanarchitecture.presentation.screens.detail.model.SpaceDetailsView
import com.example.mycleanarchitecture.presentation.screens.list.model.SpacesView
import com.example.mycleanarchitecture.presentation.util.EMPTY
import com.example.mycleanarchitecture.presentation.util.extension.convertTimestampToFormattedDate

fun Space.toSpacesView() = SpacesView(
    flightNumber = flightNumber,
    missionName = missionName,
    launchDate = launchDate,
    youtubeVideoId = videoId
)

fun Space.toSpaceDetailsView() = SpaceDetailsView(
    flightNumber = flightNumber,
    missionName = missionName,
    launchDate = launchDate,
    details = details,
    rocketName = rocketName,
    payloadMass = if (payloadMass.isNaN()) EMPTY else payloadMass.toInt().toString(),
    wikiLink = infoLink,
    youtubeVideoId = videoId
)

fun Space.toSpaceEntity() = SpaceEntity(
    flightNumber = flightNumber,
    missionName = missionName,
    details = details,
    launchDate = launchDate,
    rocketName = rocketName,
    payloadMass = if (!payloadMass.isNaN()) payloadMass else null,
    infoLink = infoLink,
    youtubeVideoId = videoId
)

fun SpaceEntity.toSpace() = Space(
    flightNumber = flightNumber,
    missionName = missionName ?: EMPTY,
    details = details ?: EMPTY,
    launchDate = launchDate ?: EMPTY,
    rocketName = rocketName ?: EMPTY,
    payloadMass = payloadMass ?: Double.NaN,
    infoLink = infoLink ?: EMPTY,
    videoId = youtubeVideoId ?: EMPTY
)

fun SpaceResponse.toSpace() = Space(
    flightNumber = flightNumber,
    missionName = missionName ?: EMPTY,
    details = details ?: EMPTY,
    launchDate = launchDateUnix?.convertTimestampToFormattedDate() ?: EMPTY,
    payloadMass = rocket?.secondStage?.payloads?.get(0)?.payloadMassKg ?: Double.NaN,
    rocketName = rocket?.rocketName ?: EMPTY,
    infoLink = links?.wikipedia ?: EMPTY,
    videoId = links?.youtubeVideoId ?: EMPTY
)