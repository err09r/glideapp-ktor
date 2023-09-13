package com.apsl.glideapp.di

import com.apsl.glideapp.common.models.Coordinates
import com.apsl.glideapp.common.models.VehicleStatus
import com.apsl.glideapp.common.models.VehicleType
import com.apsl.glideapp.common.models.ZoneType
import com.apsl.glideapp.features.ride.RideDao
import com.apsl.glideapp.features.ride.RideDaoImpl
import com.apsl.glideapp.features.route.RideCoordinatesDao
import com.apsl.glideapp.features.route.RideCoordinatesDaoImpl
import com.apsl.glideapp.features.transaction.TransactionDao
import com.apsl.glideapp.features.transaction.TransactionDaoImpl
import com.apsl.glideapp.features.user.UserDao
import com.apsl.glideapp.features.user.UserDaoImpl
import com.apsl.glideapp.features.vehicle.VehicleDao
import com.apsl.glideapp.features.vehicle.VehicleDaoImpl
import com.apsl.glideapp.features.zone.ZoneDao
import com.apsl.glideapp.features.zone.ZoneDaoImpl
import kotlin.random.Random
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.koin.dsl.module

val daoModule = module {
    single<ZoneDao>(createdAtStart = true) { ZoneDaoImpl().initializeIfEmpty() }
    single<VehicleDao> { VehicleDaoImpl().initializeIfEmpty() }
    single<UserDao> { UserDaoImpl() }
    single<RideDao> { RideDaoImpl() }
    single<RideCoordinatesDao> { RideCoordinatesDaoImpl() }
    single<TransactionDao> { TransactionDaoImpl() }
}

private fun ZoneDao.initializeIfEmpty(): ZoneDao {
    return this.apply {
        runBlocking {
            if (getZonesByType(ZoneType.Riding).isEmpty()) {
                launch {
                    insertZone(
                        code = 1,
                        title = "SL-R-1",
                        type = ZoneType.Riding,
                        coordinates = listOf(
                            Coordinates(54.45261393078834, 17.017300376477383),
                            Coordinates(54.45628227433493, 16.994285634097274),
                            Coordinates(54.463105314396195, 16.987554907552163),
                            Coordinates(54.46698175872949, 16.98905028662445),
                            Coordinates(54.487049447097625, 17.002100867619568),
                            Coordinates(54.488949566499976, 17.019637585831994),
                            Coordinates(54.473479529794574, 17.03092090065047),
                            Coordinates(54.474274399615005, 17.038125908908288),
                            Coordinates(54.47404235846866, 17.052264038319848),
                            Coordinates(54.46883342891788, 17.056886119088894),
                            Coordinates(54.45880375012081, 17.064634901555088),
                            Coordinates(54.450199279678934, 17.068985095220143),
                            Coordinates(54.44554064944707, 17.04777790110274),
                            Coordinates(54.45060434766859, 17.041796384813182),
                            Coordinates(54.459144518236315, 17.04193233251388),
                            Coordinates(54.45859630138437, 17.035407042016303),
                            Coordinates(54.45291120069188, 17.032688170975675),
                            Coordinates(54.448648095668695, 17.024667501405688),
                            Coordinates(54.45261393078834, 17.017300376477383)
                        )
                    )
                    insertZone(
                        code = 2,
                        title = "KO-R-1",
                        type = ZoneType.Riding,
                        coordinates = listOf(
                            Coordinates(54.17396235264483, 16.189101004355223),
                            Coordinates(54.173967325852175, 16.174555044287537),
                            Coordinates(54.180178395059, 16.165446826301224),
                            Coordinates(54.18089939550689, 16.15294001951405),
                            Coordinates(54.18551847251743, 16.140976991083654),
                            Coordinates(54.20405422082191, 16.149541439010385),
                            Coordinates(54.21566651294967, 16.179720907562004),
                            Coordinates(54.21352516666405, 16.18461487543516),
                            Coordinates(54.22004822587525, 16.20391885982402),
                            Coordinates(54.20869057252908, 16.213570856167117),
                            Coordinates(54.2072644359574, 16.210580098022337),
                            Coordinates(54.200908336745236, 16.21193953354265),
                            Coordinates(54.19638044852026, 16.21968831600857),
                            Coordinates(54.18676138893763, 16.24443004247901),
                            Coordinates(54.18509584639773, 16.236681260013015),
                            Coordinates(54.18136180423657, 16.232467009899935),
                            Coordinates(54.183837944426514, 16.21764916272818),
                            Coordinates(54.17493209248349, 16.201743767140336),
                            Coordinates(54.17396235264483, 16.189101004355223)
                        )
                    )
                }
            }

            if (getZonesByType(ZoneType.NoParking).isEmpty()) {
                launch {
                    insertZone(
                        code = 3,
                        title = "SL-NP-1",
                        type = ZoneType.NoParking,
                        coordinates = listOf(
                            Coordinates(54.462937, 17.032355),
                            Coordinates(54.463959, 17.028533),
                            Coordinates(54.467013, 17.027467),
                            Coordinates(54.469021, 17.029545),
                            Coordinates(54.468737, 17.032006),
                            Coordinates(54.464500, 17.035922),
                            Coordinates(54.462581, 17.033211)
                        )
                    )
                    insertZone(
                        code = 4,
                        title = "SL-NP-2",
                        type = ZoneType.NoParking,
                        coordinates = listOf(
                            Coordinates(54.448609, 17.025346),
                            Coordinates(54.453250, 17.032561),
                            Coordinates(54.454148, 17.041922),
                            Coordinates(54.448060, 17.040119)
                        )
                    )
                    insertZone(
                        code = 5,
                        title = "SL-NP-3",
                        type = ZoneType.NoParking,
                        coordinates = listOf(
                            Coordinates(54.466102, 16.995612),
                            Coordinates(54.467399, 16.984189),
                            Coordinates(54.480087, 16.989273),
                            Coordinates(54.478990, 16.998893),
                            Coordinates(54.474352, 16.997261),
                            Coordinates(54.474003, 16.999666),
                            Coordinates(54.470362, 16.998206),
                            Coordinates(54.470611, 16.995801)
                        )
                    )
                }
            }
        }
    }
}

private fun VehicleDao.initializeIfEmpty(): VehicleDao {
    return this.apply {
        runBlocking {
            if (getAllVehicles().isEmpty()) {
                repeat(200) {
                    launch {
                        insertVehicle(
                            code = it + 1,
                            zoneCode = if (it in 0..100) 1 else 2,
                            batteryCharge = Random.nextInt(40, 100),
                            type = VehicleType.Scooter,
                            status = VehicleStatus.Available,
                            coordinates = Coordinates(0.0, 0.0)
                        )
                    }
                }
            }
        }
    }
}
