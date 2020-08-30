package pl.amistad.exercise.database.elevation

import pl.amistad.exercise.elevation.ElevationRequest

class ElevationCallEntity(
    val elevationRequest: ElevationRequest,
    val result: Double,
    val id: Int = 0
)