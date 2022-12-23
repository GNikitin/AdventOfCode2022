package me.nikigle.aoc.day22

import me.nikigle.aoc.day22.Direction.*
import java.io.BufferedReader
import java.io.InputStreamReader
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
            val (newCoordinate, newDirection) = move(map, direction, move.toInt(), coordinate)
            coordinate = newCoordinate
            direction = newDirection
        }
    }
    println("${(coordinate.x + 1) * 4 + (coordinate.y + 1) * 1000 + direction.ordinal}")
}

private fun move(map: List<String>, initDirection: Direction, steps: Int, coordinate: Coordinate): Pair<Coordinate, Direction> {
    var y = coordinate.y
    var x = coordinate.x
    var nextX: Int
    var nextY: Int
    var direction = initDirection
    var nextDirection = direction
    var stepsLeft = steps
    while (stepsLeft != 0) {

        if (y == 0 && direction == UP) {
            if (x >= 100) {
                nextX = x - 100
                nextY = 199
            } else {
                nextDirection = RIGHT
                nextX = 0
                nextY = x + 100
            }
        } else if (x == 0 && direction == LEFT) {
            if (y >= 150) {
                nextDirection = DOWN
                nextY = 0
                nextX = y - 100
            } else {
                nextDirection = RIGHT
                nextX = 50
                nextY = 149 - y
            }
        } else if (y == 199 && direction == DOWN) {
            nextX = x + 100
            nextY = 0
        } else if (x == 50 && direction == LEFT) {
            if (y < 50) {
                nextDirection = RIGHT
                nextX = 0
                nextY = 149 - y
            } else if (y < 100) {
                nextDirection = DOWN
                nextY = 100
                nextX = y - 50
            } else {
                nextX = x + direction.coordinate.x
                nextY = y + direction.coordinate.y
            }
        } else if (x == 99 && direction == RIGHT) {
            if (y >= 100) {
                nextDirection = LEFT
                nextX = 149
                nextY = 149 - y
            } else if (y >= 50) {
                nextDirection = UP
                nextY = 49
                nextX = y + 50
            } else {
                nextX = x + direction.coordinate.x
                nextY = y + direction.coordinate.y
            }
        } else if (y == 100 && x < 50 && direction == UP) {
            nextDirection = RIGHT
            nextX = 50
            nextY = x + 50
        } else if (x >= 100 && y == 49 && direction == DOWN) {
            nextDirection = LEFT
            nextX = 99
            nextY = x - 50
        } else if (x == 49 && y >= 150 && direction == RIGHT) {
            nextDirection = UP
            nextY = 149
            nextX = y - 100
        } else if (x >= 50 && y == 149 && direction == DOWN) {
            nextDirection = LEFT
            nextX = 49
            nextY = x + 100
        } else if (x == 149 && direction == RIGHT) {
            nextDirection = LEFT
            nextX = 99
            nextY = 149 - y
        } else {
            nextX = x + direction.coordinate.x
            nextY = y + direction.coordinate.y
        }

        if (map[nextY][nextX] == '#') {
            return Pair(Coordinate(y, x), direction)
        } else {
            stepsLeft--
            y = nextY
            x = nextX
            direction = nextDirection
        }

    }
    return Pair(Coordinate(y, x), direction)
}
