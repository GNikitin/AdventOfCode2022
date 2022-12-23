package me.nikigle.aoc.day22

import java.util.regex.Pattern

data class Coordinate(val y: Int, val x: Int)

val PATTERN = Pattern.compile("(\\d+|R|L)+?")
enum class Direction(
    val coordinate: Coordinate,
    val image: Char
) {
    RIGHT(Coordinate(0, 1), '>') {
        override fun left(): Direction {
            return UP
        }

        override fun right(): Direction {
            return DOWN
        }

    },
    DOWN(Coordinate(1, 0), 'v') {
        override fun left(): Direction {
            return RIGHT
        }

        override fun right(): Direction {
            return LEFT
        }

    },
    LEFT(Coordinate(0, -1), '<') {
        override fun left(): Direction {
            return DOWN
        }

        override fun right(): Direction {
            return UP
        }

    },
    UP(Coordinate(-1, 0),'^') {
        override fun left(): Direction {
            return LEFT
        }

        override fun right(): Direction {
            return RIGHT
        }
    },
    ;


    abstract fun left(): Direction
    abstract fun right(): Direction
}