package com.example.mycleanarchitecture.framework.network.model

import com.google.gson.annotations.SerializedName

data class OrbitParams (
    @SerializedName("reference_system") val referenceSystem: String?,
    @SerializedName("regime") val regime: String?,
    @SerializedName("longitude") val longitude: Double?,
    @SerializedName("semi_major_axis_km") val semiMajorAxisKm: Double?,
    @SerializedName("eccentricity") val eccentricity: Double?,
    @SerializedName("periapsis_km") val periapsisKm: Double?,
    @SerializedName("apoapsis_km") val apoapsisKm: Double?,
    @SerializedName("inclination_deg") val inclinationDeg: Double?,
    @SerializedName("period_min") val periodMin: Double?,
    @SerializedName("lifespan_years") val lifespanYears: Double?
)