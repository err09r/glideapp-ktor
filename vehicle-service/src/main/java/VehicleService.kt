import kotlin.coroutines.coroutineContext
import kotlin.random.Random
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.isActive

object VehicleService {

    private const val generationInterval: Long = 60_000L

    val ridingZones: List<ZoneInfo> = listOf(
        ZoneInfo(
            code = 1,
            title = "Słupsk",
            type = ZoneType.Riding,
            coordinates = listOf(
                Point(54.45261393078834, 17.017300376477383),
                Point(54.45628227433493, 16.994285634097274),
                Point(54.463105314396195, 16.987554907552163),
                Point(54.46698175872949, 16.98905028662445),
                Point(54.487049447097625, 17.002100867619568),
                Point(54.488949566499976, 17.019637585831994),
                Point(54.473479529794574, 17.03092090065047),
                Point(54.474274399615005, 17.038125908908288),
                Point(54.47404235846866, 17.052264038319848),
                Point(54.46883342891788, 17.056886119088894),
                Point(54.45880375012081, 17.064634901555088),
                Point(54.450199279678934, 17.068985095220143),
                Point(54.44554064944707, 17.04777790110274),
                Point(54.45060434766859, 17.041796384813182),
                Point(54.459144518236315, 17.04193233251388),
                Point(54.45859630138437, 17.035407042016303),
                Point(54.45291120069188, 17.032688170975675),
                Point(54.448648095668695, 17.024667501405688),
                Point(54.45261393078834, 17.017300376477383)
            )
        ),
        ZoneInfo(
            code = 2,
            title = "Koszalin",
            type = ZoneType.Riding,
            coordinates = listOf(
                Point(54.17396235264483, 16.189101004355223),
                Point(54.173967325852175, 16.174555044287537),
                Point(54.180178395059, 16.165446826301224),
                Point(54.18089939550689, 16.15294001951405),
                Point(54.18551847251743, 16.140976991083654),
                Point(54.20405422082191, 16.149541439010385),
                Point(54.21566651294967, 16.179720907562004),
                Point(54.21352516666405, 16.18461487543516),
                Point(54.22004822587525, 16.20391885982402),
                Point(54.20869057252908, 16.213570856167117),
                Point(54.2072644359574, 16.210580098022337),
                Point(54.200908336745236, 16.21193953354265),
                Point(54.19638044852026, 16.21968831600857),
                Point(54.18676138893763, 16.24443004247901),
                Point(54.18509584639773, 16.236681260013015),
                Point(54.18136180423657, 16.232467009899935),
                Point(54.183837944426514, 16.21764916272818),
                Point(54.17493209248349, 16.201743767140336),
                Point(54.17396235264483, 16.189101004355223)
            )
        )
    )

    val availableVehicles: Flow<List<VehicleInfo>> = flow {
        while (coroutineContext.isActive) {
            emit(
                (1..50).map {
                    VehicleInfo(
                        code = Random.nextInt(1, 50),
                        zoneCode = 1,
                        batteryCharge = Random.nextInt(40, 101),
                        type = VehicleType.Scooter,
                        status = VehicleStatus.Available,
                        coordinates = generateCoordinatesInsideZone(ridingZones[0])
                    )
                } + (1..50).map {
                    VehicleInfo(
                        code = Random.nextInt(51, 100),
                        zoneCode = 2,
                        batteryCharge = Random.nextInt(40, 101),
                        type = VehicleType.Scooter,
                        status = VehicleStatus.Available,
                        coordinates = generateCoordinatesInsideZone(ridingZones[1]),
                    )
                }
            )

            delay(generationInterval)
        }
    }.flowOn(Dispatchers.Default)

    private fun generateCoordinatesInsideZone(zone: ZoneInfo): Point {
        val leftmostPoint = zone.coordinates.minOf { it.longitude }
        val rightmostPoint = zone.coordinates.maxOf { it.longitude }
        val highestPoint = zone.coordinates.minOf { it.latitude }
        val lowestPont = zone.coordinates.maxOf { it.latitude }

        var generatedLongitude: Double
        var generatedLatitude: Double

        do {
            generatedLatitude = Random.nextDouble(highestPoint, lowestPont)
            generatedLongitude = Random.nextDouble(leftmostPoint, rightmostPoint)
        } while (!isInsideOfPolygon(
                numberOfVertices = zone.coordinates.size,
                vertX = zone.coordinates.map { it.longitude },
                vertY = zone.coordinates.map { it.latitude },
                pointX = generatedLongitude,
                pointY = generatedLatitude
            )
        )
        return Point(
            latitude = generatedLatitude,
            longitude = generatedLongitude
        )
    }
}
