package me.nikigle.aoc.day15

import java.util.ArrayList
import java.util.regex.Pattern
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

val PATTERN = Pattern.compile("Sensor at x=(-?\\d*), y=(-?\\d*): closest beacon is at x=(-?\\d*), y=(-?\\d*)")


fun coordinatesAtDistanceAtRow(c: Coordinate, distance: Int, row: Int, maxX: Int) : List<Coordinate> {
    if (abs(c.y - row) > distance) return listOf()
    var deltaX = distance - abs(c.y - row)
    return ArrayList<Coordinate>().apply {
        for (i in max(0, c.x - deltaX)..min(c.x + deltaX, maxX)) {
            add(Coordinate(i, row))
        }
    }
}

data class Coordinate(val x: Int, val y: Int) {
    operator fun minus(other: Coordinate): Int {
        return abs(x - other.x) + abs(y - other.y)
    }

    operator fun rangeTo(distance: Int): List<Coordinate> {
        return ArrayList<Coordinate>().apply {
            for (i in max(0, x - distance)..x + distance) {
                for (j in max(0, y - distance)..y + distance) {
                    if (this@Coordinate - Coordinate(i, j) <= distance) {
                        add(Coordinate(i, j))
                    }
                }
            }
        }
    }

    operator fun contains(max: Coordinate): Boolean {
        return max.x >= x && max.y >= y
    }
}