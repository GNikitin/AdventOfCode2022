package me.nikigle.aoc.day23

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.min
import kotlin.math.max

fun main() {
    val reader =
        BufferedReader(InputStreamReader(Thread.currentThread().contextClassLoader.getResourceAsStream("elves.txt")))
    var line = reader.readLine()

    var elves = HashSet<Coordinate>()
    var index = 0

    do {
        line.forEachIndexed { i, c ->
            if (c == '#') {
                elves.add(Coordinate(index, i))
            }
        }
        line = reader.readLine()
        index++
    } while (line != null)

    val checks = Array<(s: HashSet<Coordinate>, c: Coordinate) -> Coordinate?>(4) {
        when (it) {
            0 -> ::checkNorth
            1 -> ::checkSouth
            2 -> ::checkWest
            else -> ::checkEast
        }
    }

    var round = 0
    while (true) {
        val nextElves = HashSet<Coordinate>()
        val proposedSteps = HashMap<Coordinate, Set<Coordinate>>()
        elves.forEach { coord ->
            val firstCheck = checks[round.mod(4)](elves, coord)
            val secondCheck = checks[(round + 1).mod(4)](elves, coord)
            val thirdCheck = checks[(round + 2).mod(4)](elves, coord)
            val fourthCheck = checks[(round + 3).mod(4)](elves, coord)
            val nextCoord =
                if (firstCheck != null && secondCheck != null && thirdCheck != null && fourthCheck != null) coord
                else firstCheck ?: secondCheck ?: thirdCheck ?: fourthCheck ?: coord
            if (nextCoord == coord) {
                nextElves.add(coord)
            } else {
                proposedSteps.merge(nextCoord, setOf(coord)) { a, b -> a + b }
            }
        }
        if (proposedSteps.isEmpty()) {
            break
        }
        proposedSteps.forEach {
            if (it.value.size == 1) {
                nextElves.add(it.key)
            } else {
                nextElves.addAll(it.value)
            }
        }
        if (elves.size != nextElves.size) println("WTF????")
        elves = nextElves
        round++
    }

    println(round + 1)
}