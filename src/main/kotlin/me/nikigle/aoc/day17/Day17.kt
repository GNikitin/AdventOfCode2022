package me.nikigle.aoc.day17

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Integer.max

fun main() {
    val gas = Thread.currentThread().contextClassLoader.getResourceAsStream("gas.txt")
        ?.let { InputStreamReader(it) }?.let { BufferedReader(it).readLine() }!!

    val pushes = gasPushSequence(gas).iterator()
    val rocks = rockSequence().iterator()
    var maxHeight = 0
    val room = ArrayList<CharArray>()
    repeat(2022) {
        addHeight(room, maxHeight)
        var coordinate = Coordinate(maxHeight + 3, 2)
        val rock = rocks.next()
        var falling = true
        while (falling) {

            var newCoordinate = coordinate + pushes.next()
            if (validRockCoordinate(room, rock, newCoordinate)) {
                coordinate = newCoordinate
            }
            newCoordinate = Coordinate(coordinate.vert - 1, coordinate.hor)
            if (validRockCoordinate(room, rock, newCoordinate)) {
                coordinate = newCoordinate
            } else {
                falling = false
                rock.forEach{
                    room[coordinate.vert + it.vert][coordinate.hor + it.hor] = '#'
                    maxHeight = max(maxHeight, coordinate.vert + it.vert + 1)
                }
            }
        }

    }
    printRoom(room, 20)
    println(maxHeight)
}
