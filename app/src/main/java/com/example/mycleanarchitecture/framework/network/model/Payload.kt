package com.example.mycleanarchitecture.framework.network.model

import com.google.gson.annotations.SerializedName

data class Payload (
    @SerializedName("payload_id") val payloadId: String?,
    @SerializedName("reused") val reused: Boolean?,
    @SerializedName("customers") val customers: List<String>?,
    @SerializedName("payload_type") val payloadType: String?,
    @SerializedName("payload_mass_kg") val payloadMassKg: Double?,
    @SerializedName("payload_mass_lbs") val payloadMassLbs: Double?,
    @SerializedName("orbit") val orbit: String?,
    @SerializedName("orbit_params") val orbitParams: OrbitParams?
)