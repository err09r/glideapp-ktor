package com.apsl.glideapp.utils

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
