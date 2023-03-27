data class VehicleInfo(
    val code: Int,
    val zoneCode: Int,
    val batteryCharge: Int,
    val type: VehicleType,
    val status: VehicleStatus,
    val coordinates: Point
)
