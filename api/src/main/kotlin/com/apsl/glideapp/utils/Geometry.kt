package com.apsl.glideapp.utils

import com.apsl.glideapp.common.models.Coordinates
import kotlin.math.asin
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

private const val EARTH_RADIUS_METERS = 6_371_000

/**
 * Checks whether the point is inside the polygon.
 *
 * @param numberOfVertices number of polygon's vertices.
 * @param vertX list containing the x-coordinates of the polygon's vertices.
 * @param vertY list containing the y-coordinates of the polygon's vertices.
 * @param pointX the x-coordinate of the point for research.
 * @param pointY the y-coordinate of the point for research.
 * @see <a href="https://wrf.ecse.rpi.edu/Research/Short_Notes/pnpoly.html">PNPOLY - Point Inclusion in Polygon Test</a>
 */
fun isInsideOfPolygon(
    numberOfVertices: Int,
    vertX: List<Double>,
    vertY: List<Double>,
    pointX: Double,
    pointY: Double
): Boolean {
    var isInside = false
    var i = 0
    var j = numberOfVertices - 1
    while (i < numberOfVertices) {
        if (vertY[i] > pointY != vertY[j] > pointY && pointX < (vertX[j] - vertX[i]) * (pointY - vertY[i]) / (vertY[j] - vertY[i]) + vertX[i]) {
            isInside = !isInside
        }
        j = i++
    }
    return isInside
}

/**
 * Calculates the total distance of ride route in meters.
 *
 * @param route list of [Coordinates] describing ride route.
 * @see <a href="https://en.wikipedia.org/wiki/Haversine_formula">Haversine formula</a>
 * @see <a href="https://community.esri.com/t5/coordinate-reference-systems-blog/distance-on-a-sphere-the-haversine-formula/ba-p/902128">Distance on a sphere: The Haversine Formula</a>
 * @return distance in meters
 */
fun calculateDistance(vararg route: Coordinates): Double {
    var distance = 0.0

    if (route.size < 2) {
        return distance
    }

    for (index in route.indices) {
        val nextIndex = index + 1
        if (nextIndex <= route.lastIndex) {
            val lat1 = Math.toRadians(route[index].latitude)
            val long1 = Math.toRadians(route[index].longitude)
            val lat2 = Math.toRadians(route[nextIndex].latitude)
            val long2 = Math.toRadians(route[nextIndex].longitude)

            distance += 2 * EARTH_RADIUS_METERS *
                    asin(
                        sqrt(
                            sin((lat2 - lat1) / 2)
                                    * sin((lat2 - lat1) / 2)
                                    + cos(lat1) * cos(lat2)
                                    * sin((long2 - long1) / 2)
                                    * sin((long2 - long1) / 2)
                        )
                    )
        }
    }

    return distance
}
