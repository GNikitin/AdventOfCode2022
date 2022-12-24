package me.nikigle.aoc.day24



data class Coordinate(val y: Int, val x: Int) : Comparable<Coordinate> {
    override fun compareTo(other: Coordinate): Int {
        return y + x - other.x - other.y
    }
}

enum class Direction(
    val coordinate: Coordinate,
    val image: Char
) {
    RIGHT(Coordinate(0, 1), '>'),
    DOWN(Coordinate(1, 0), 'v'),
    LEFT(Coordinate(0, -1), '<'),
    UP(Coordinate(-1, 0), '^'),
    ;
}

val directionImages: Map<Char, Direction> =
    HashMap<Char, Direction>().apply { Direction.values().forEach { this[it.image] = it } }

data class Blizzard(val coordinate: Coordinate, val direction: Direction)

data class State(val blizzards: Set<Blizzard>, val coordinate: Coordinate, val minutesPassed: Int)

fun moveBlizzards(blizzards: Set<Blizzard>, height: Int, width: Int): Set<Blizzard> {
    var result = HashSet<Blizzard>()
    blizzards.forEach {
        val newX = (it.coordinate.x + it.direction.coordinate.x).mod(width)
        val newY = (it.coordinate.y + it.direction.coordinate.y).mod(height)
        result.add(Blizzard(Coordinate(newY, newX), it.direction))
    }
    return result
}

fun getSurroundingCoordinates(coordinate: Coordinate): List<Coordinate> {
    return mutableListOf(coordinate).apply {
        addAll(
            Direction.values().map { Coordinate(coordinate.y + it.coordinate.y, coordinate.x + it.coordinate.x) }
                .toList()
        )
    }
}