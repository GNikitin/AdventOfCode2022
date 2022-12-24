package me.nikigle.aoc.day24

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.collections.HashSet
import kotlin.math.abs
import kotlin.math.min

fun main() {
    val reader =
        BufferedReader(InputStreamReader(Thread.currentThread().contextClassLoader.getResourceAsStream("blizzards.txt")))
    reader.readLine()
    var line = reader.readLine()
    val width = line.length - 2
    var height = 0
    val blizzards = HashSet<Blizzard>()

    do {
        line.filter { it != '#' }
            .forEachIndexed { i, c -> directionImages[c]?.let { blizzards.add(Blizzard(Coordinate(height, i), it)) } }
        height++
        line = reader.readLine()
    } while (line != null)
    height--

    val goal = Coordinate(height, width - 1)
    val start = Coordinate(-1, 0)

    var goalState = reachGoalCoord(goal, start, blizzards)
    var minutes = goalState.minutesPassed
    println("Part 1 - $minutes")
    goalState = reachGoalCoord(start, goal, goalState.blizzards)
    minutes += goalState.minutesPassed
    goalState = reachGoalCoord(goal, start, goalState.blizzards)
    minutes += goalState.minutesPassed
    println("Part 2 - $minutes")
}
fun reachGoalCoord(goal: Coordinate, start: Coordinate, blizzards: Set<Blizzard>) : State {
    val width = abs(goal.x - start.x) + 1
    val height = abs(goal.y - start.y) - 1
    var minMinutes = (width + height) * 10
    val blizzardsStateCache = HashMap<Int, Set<Blizzard>>()
    val blizzardsCoordsStateCache = HashMap<Int, Set<Coordinate>>()
    val q = LinkedList<State>().apply { add(State(blizzards, start, 0)) }
    val states = HashSet<Pair<Int, Coordinate>>()
    while (!q.isEmpty()) {
        val state = q.removeFirst()
        if (state.coordinate == goal) {
            minMinutes = min(minMinutes, state.minutesPassed)
            continue
        }
        if (minMinutes < state.minutesPassed + goal.x + goal.y - state.coordinate.x - state.coordinate.y) {
            continue
        }
        val nextBlizzards = blizzardsStateCache.computeIfAbsent(state.minutesPassed + 1) {
            moveBlizzards(state.blizzards, height, width)
        }
        val nextBlizzardsCoords = blizzardsCoordsStateCache.computeIfAbsent(state.minutesPassed + 1) {
            nextBlizzards.map { it.coordinate }.toSet()
        }

        getSurroundingCoordinates(state.coordinate).filter {
            it == goal || it == start ||
                    (!nextBlizzardsCoords.contains(it) &&
                            it.x < width && it.x >= 0 && it.y < height && it.y >= 0)
        }.filter { !states.contains(Pair(state.minutesPassed + 1, it))}.
        sorted().forEach {
            q.addFirst(State(nextBlizzards, it, state.minutesPassed + 1))
            states.add(Pair(state.minutesPassed + 1, it))
        }
    }

    return State(blizzardsStateCache[minMinutes]!!, goal, minMinutes)
}