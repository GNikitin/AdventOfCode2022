package me.nikigle.aoc.day15

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
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
    val testRowIndex = 10 - yOffset
    val maxX = maxSensorX - xOffset + 1 + maxDistance
    val maxY = max(testRowIndex, maxSensorY - yOffset) + 1

    val testRowImpossible = HashSet<Int>()
    sensorsToBeacons
        .map {
            Pair(
                Coordinate(it.first.x - xOffset, it.first.y - yOffset),
                Coordinate(it.second.x - xOffset, it.second.y - yOffset)
            )
        }
        .forEach {
            val distance = it.first - it.second
            coordinatesAtDistanceAtRow(it.first, distance, testRowIndex, maxX)
                .forEach { c ->
                    if (!(it.second.x == c.x && it.second.y == c.y)) {
                        testRowImpossible.add(c.x)
                    }
                }

        }
    println(testRowImpossible.size)
}

