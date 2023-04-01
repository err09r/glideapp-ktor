import kotlin.coroutines.coroutineContext
import kotlin.random.Random
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.isActive

object VehicleService {

    private const val generationInterval: Long = 120_000L

    val ridingZones: List<ZoneInfo> = listOf(
        ZoneInfo(
            code = 1,
            title = "Słupsk",
            type = 0,
            coordinates = listOf(
                54.45261393078834 to 17.017300376477383,
                54.45628227433493 to 16.994285634097274,
                54.463105314396195 to 16.987554907552163,
                54.46698175872949 to 16.98905028662445,
                54.487049447097625 to 17.002100867619568,
                54.488949566499976 to 17.019637585831994,
                54.473479529794574 to 17.03092090065047,
                54.474274399615005 to 17.038125908908288,
                54.47404235846866 to 17.052264038319848,
                54.46883342891788 to 17.056886119088894,
                54.45880375012081 to 17.064634901555088,
                54.450199279678934 to 17.068985095220143,
                54.44554064944707 to 17.04777790110274,
                54.45060434766859 to 17.041796384813182,
                54.459144518236315 to 17.04193233251388,
                54.45859630138437 to 17.035407042016303,
                54.45291120069188 to 17.032688170975675,
                54.448648095668695 to 17.024667501405688,
                54.45261393078834 to 17.017300376477383
            )
        ),
        ZoneInfo(
            code = 2,
            title = "Koszalin",
            type = 0,
            coordinates = listOf(
                54.17396235264483 to 16.189101004355223,
                54.173967325852175 to 16.174555044287537,
                54.180178395059 to 16.165446826301224,
                54.18089939550689 to 16.15294001951405,
                54.18551847251743 to 16.140976991083654,
                54.20405422082191 to 16.149541439010385,
                54.21566651294967 to 16.179720907562004,
                54.21352516666405 to 16.18461487543516,
                54.22004822587525 to 16.20391885982402,
                54.20869057252908 to 16.213570856167117,
                54.2072644359574 to 16.210580098022337,
                54.200908336745236 to 16.21193953354265,
                54.19638044852026 to 16.21968831600857,
                54.18676138893763 to 16.24443004247901,
                54.18509584639773 to 16.236681260013015,
                54.18136180423657 to 16.232467009899935,
                54.183837944426514 to 16.21764916272818,
                54.17493209248349 to 16.201743767140336,
                54.17396235264483 to 16.189101004355223
            )
        )
    )

    val availableVehicles: Flow<List<VehicleInfo>> = flow {
        while (coroutineContext.isActive) {
            emit(
                (1..50).map {
                    val coordinates = generateCoordinatesInsideZone(ridingZones[0])
                    VehicleInfo(
                        code = Random.nextInt(1, 50),
                        zoneCode = 1,
                        batteryCharge = Random.nextInt(40, 101),
                        type = 0,
                        status = 0,
                        latitude = coordinates.first,
                        longitude = coordinates.second,
                    )
                } + (1..50).map {
                    val coordinates = generateCoordinatesInsideZone(ridingZones[1])
                    VehicleInfo(
                        code = Random.nextInt(51, 100),
                        zoneCode = 2,
                        batteryCharge = Random.nextInt(40, 101),
                        type = 0,
                        status = 0,
                        latitude = coordinates.first,
                        longitude = coordinates.second,
                    )
                }
            )

            delay(generationInterval)
        }
    }.flowOn(Dispatchers.Default)

    private fun generateCoordinatesInsideZone(zone: ZoneInfo): Pair<Double, Double> {
        val leftmostPoint = zone.coordinates.minOf { it.second }
        val rightmostPoint = zone.coordinates.maxOf { it.second }
        val highestPoint = zone.coordinates.minOf { it.first }
        val lowestPont = zone.coordinates.maxOf { it.first }

        var generatedLongitude: Double
        var generatedLatitude: Double

        do {
            generatedLatitude = Random.nextDouble(highestPoint, lowestPont)
            generatedLongitude = Random.nextDouble(leftmostPoint, rightmostPoint)
        } while (!isInsideOfPolygon(
                numberOfVertices = zone.coordinates.size,
                vertX = zone.coordinates.map { it.second },
                vertY = zone.coordinates.map { it.first },
                pointX = generatedLongitude,
                pointY = generatedLatitude
            )
        )

        return generatedLatitude to generatedLongitude
    }
}
