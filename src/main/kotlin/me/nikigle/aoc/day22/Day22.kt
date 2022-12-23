package me.nikigle.aoc.day22

import me.nikigle.aoc.day22.Direction.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.regex.Pattern
import kotlin.math.abs
import kotlin.math.max

fun main() {
    val reader =
        BufferedReader(InputStreamReader(Thread.currentThread().contextClassLoader.getResourceAsStream("monkeys-map.txt")))
    var line = reader.readLine()

    val map = ArrayList<String>()
    var maxLength = 0
    do {
        maxLength = max(maxLength, line.length)
        map.add(line)
        line = reader.readLine()
    } while (line.isNotEmpty())

    var route = reader.readLine()

    map.apply {
        indices.forEach { i ->
            var toFill = maxLength - this[i].length
            if (toFill > 0) {
                add(i, removeAt(i) + " ".repeat(toFill))
            }
        }
    }
//    map.forEach{ println(it) }

    processRoute(map, route)
}

private fun processRoute(map: List<String>, routeString: String) {
    val matcher = PATTERN.matcher(routeString)
    var direction = RIGHT
    var coordinate = Coordinate(0, map[0].indexOfFirst { it == '.' })
    while (matcher.find()) {
        val move = matcher.group(1)
        if (move == "R" || move == "L") {
            direction = if (move == "R") direction.right() else direction.left()



        } else {
            coordinate = move(map, direction, move.toInt(), coordinate)
        }
    }
    println("${(coordinate.x + 1) * 4 + (coordinate.y + 1) * 1000 + direction.ordinal}")
}

private fun move(map: List<String>, direction: Direction, steps: Int, coordinate: Coordinate): Coordinate {
    var y = coordinate.y
    var x = coordinate.x
    var nextX = x
    var nextY = y
    var stepsLeft = steps
    while (stepsLeft != 0) {

        nextX = (nextX + direction.coordinate.x).mod(map[0].length)
        nextY = (nextY + direction.coordinate.y).mod(map.size)

        if (map[nextY][nextX] == '#') {
            return Coordinate(y, x)
        }
        if (map[nextY][nextX] == '.') {
            stepsLeft--
            y = nextY
            x = nextX
        }

    }
    return Coordinate(y, x)
}
