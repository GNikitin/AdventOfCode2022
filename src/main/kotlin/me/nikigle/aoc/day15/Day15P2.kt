package me.nikigle.aoc.day15

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

fun main() {
    val reader =
        BufferedReader(InputStreamReader(Thread.currentThread().contextClassLoader.getResourceAsStream("sensors.txt")))
    var line = reader.readLine()
    val sensorsToBeacons = LinkedList<Pair<Coordinate, Coordinate>>()
    var xOffset = 0
    var yOffset = 0
    var maxSensorX = 0
    var maxSensorY = 0
    var maxDistance = 0
    do {
        PATTERN.matcher(line!!).apply {
            find()
            val (sx, sy, bx, by) = mutableListOf<Int>().apply { repeat(4) { add(group(it + 1).toInt()) } }
            xOffset = min(min(sx, bx), xOffset)
            yOffset = min(min(sy, by), yOffset)
            maxSensorX = max(sx, maxSensorX)
            maxSensorY = max(sy, maxSensorY)
            val s = Coordinate(sx, sy)
            val b = Coordinate(bx, by)
            maxDistance = max(s - b, maxDistance)

            sensorsToBeacons.add(Pair(s, b))
        }
        line = reader.readLine()

    } while (line != null)



    val maxCoordinate = 4000000
    val minCoordinateX = 0 - xOffset
    val maxCoordinateX = maxCoordinate - xOffset
    val maxCoordinateY = maxCoordinate - yOffset
    outer@ for (testRowIndex in 0 - yOffset..maxCoordinateY) {
        val offsetSensorsToBeacons = sensorsToBeacons
            .map {
                Pair(
                    Coordinate(it.first.x - xOffset, it.first.y - yOffset),
                    Coordinate(it.second.x - xOffset, it.second.y - yOffset)
                )
            }
        var ranges = LinkedList<Pair<Int, Int>>()
        for (s2b in offsetSensorsToBeacons) {
            val distance = s2b.first - s2b.second
            if (abs(s2b.first.y - testRowIndex) <= distance) {
                val deltaX = distance - abs(s2b.first.y - testRowIndex)
                var newRange = xCoordinatesWithoutBeaconAtRow(deltaX, s2b.first.x, minCoordinateX, maxCoordinateX)
                if (newRange.first == minCoordinateX && newRange.second == maxCoordinateX) {
                    continue@outer
                }

                val mergedRanges = LinkedList<Pair<Int, Int>>()
                while (ranges.isNotEmpty()) {
                    val existingRange = ranges.remove()
                    if ((newRange.first in existingRange.first..existingRange.second)
                        || (existingRange.first in newRange.first..newRange.second)
                    ) {
                        newRange =
                            Pair(min(existingRange.first, newRange.first), max(existingRange.second, newRange.second))
                        if (newRange.first == minCoordinateX && newRange.second == maxCoordinateX) {
                            continue@outer
                        }
                    } else {
                            mergedRanges.add(existingRange)
                    }
                }
                mergedRanges.add(newRange)
                ranges = mergedRanges
            }
        }
        if (ranges.size != 1) {
            println(xOffset)
            println(yOffset)
            println((testRowIndex + yOffset).toString() + " -> " + ranges.toString())
            print((ranges[0].second + 1 + xOffset).toLong() * 4000000 + testRowIndex + yOffset)
        }

    }
}

fun xCoordinatesWithoutBeaconAtRow(deltaX: Int, x: Int, minX: Int, maxX: Int): Pair<Int, Int> {
    return Pair(max(minX, x - deltaX), min(maxX, x + deltaX))
}