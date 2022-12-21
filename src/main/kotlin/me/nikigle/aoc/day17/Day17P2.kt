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
    var room = ArrayList<CharArray>()
    val statesMap = HashMap<TopState, Pair<Int, Int>>()
    var gasIndex = 0
    var countTail = false
    var cycle = 0
    var totalHeight = 0L
    var anchorHeight = 0
    var cyclesLeft = 1L
    while (cyclesLeft != 0L) {

        addHeight(room, maxHeight)
        var coordinate = Coordinate(maxHeight + 3, 2)
        val rock = rocks.next()
        var falling = true
        while (falling) {
            var newCoordinate = coordinate + pushes.next()
            gasIndex = (gasIndex + 1) % gas.length
            if (validRockCoordinate(room, rock, newCoordinate)) {
                coordinate = newCoordinate
            }
            newCoordinate = Coordinate(coordinate.vert - 1, coordinate.hor)
            if (validRockCoordinate(room, rock, newCoordinate)) {
                coordinate = newCoordinate
            } else {
                falling = false
                rock.forEach {
                    room[coordinate.vert + it.vert][coordinate.hor + it.hor] = '#'
                    maxHeight = max(maxHeight, coordinate.vert + it.vert + 1)
                }
            }
        }
        if (countTail) {
            cyclesLeft--
        } else if (maxHeight >= 100) {
            var stateList = ArrayList<String>()
            for (i in maxHeight - 25 until maxHeight) {
                stateList.add(String(room[i]))
            }
            val state = TopState(stateList, gasIndex, cycle % 5)
            if (statesMap[state] != null) {
                countTail = true
                var savedState = statesMap[state]!!
                var cycleLength = cycle - savedState.first
                totalHeight = savedState.second.toLong()
                totalHeight += (1_000_000_000_000L - savedState.first) / cycleLength * (maxHeight - savedState.second)
                anchorHeight = maxHeight
                cyclesLeft = (1_000_000_000_000L - savedState.first) % cycleLength - 1

            } else {
                statesMap[state] = Pair(cycle, maxHeight)
            }
        }
        cycle++
    }
    println(totalHeight + maxHeight - anchorHeight)
}
