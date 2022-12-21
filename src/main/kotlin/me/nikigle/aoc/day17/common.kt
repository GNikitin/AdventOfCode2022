package me.nikigle.aoc.day17

fun gasPushSequence(gas: String): Sequence<Int> {
    var count = 0
    return generateSequence {
        when (gas[count++%gas.length]) {
            '>' -> 1
            else -> -1
        }
    }
}
fun rockSequence(): Sequence<List<Coordinate>> {
    var count = 0
    val rocks = listOf(
        listOf(Coordinate(0, 0), Coordinate(0,1), Coordinate(0, 2), Coordinate(0, 3)),
        listOf(Coordinate(1, 0), Coordinate(0, 1), Coordinate(1, 1), Coordinate(2, 1), Coordinate(1, 2)),
        listOf(Coordinate(0, 0), Coordinate(0, 1), Coordinate(0, 2), Coordinate(1, 2), Coordinate(2, 2)),
        listOf(Coordinate(0, 0), Coordinate(1, 0), Coordinate(2, 0), Coordinate(3, 0)),
        listOf(Coordinate(0, 0), Coordinate(1, 0), Coordinate(0, 1), Coordinate(1,1))
    )
    return generateSequence { rocks[count++%rocks.size] }
}

fun printRoom(room: List<CharArray>, top: Int) {
    for (i in room.size - 1 downTo room.size - top) {
        println("|${room[i].joinToString("")}|")
    }
//    println("+-------+")
//    println()
}

fun addHeight(room: ArrayList<CharArray>, maxHeight: Int) {
    repeat(maxHeight + 7 - room.size) {
        room.add(CharArray(7) { '.' })
    }
}

fun validRockCoordinate(room: List<CharArray>, rock: List<Coordinate>, newCoordinate: Coordinate): Boolean {
    rock.forEach {
        val hor = it.hor + newCoordinate.hor
        val vert = it.vert + newCoordinate.vert
        if ( vert < 0 || hor < 0 || hor > room[vert].size - 1 || room[vert][hor] == '#') {
            return false
        }
    }
    return true
}

data class Coordinate(val vert: Int, val hor : Int) {
    operator fun plus(horOffset: Int): Coordinate {
        return Coordinate(vert, hor + horOffset)

    }
}

data class TopState(val rocks: List<String>, val gasIndex: Int, val rockIndex: Int)

fun main() {
    var set = HashSet<TopState>()
    set.add(TopState(listOf("a"), 0, 1))
    println(set.contains(TopState(listOf("a"), 0, 1)))
    println(listOf("a".toCharArray()).hashCode())
    println(listOf("a".toCharArray()).hashCode())
}