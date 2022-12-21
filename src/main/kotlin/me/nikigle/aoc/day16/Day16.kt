package me.nikigle.aoc.day16

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.collections.HashMap
import kotlin.math.max

fun main() {
    val reader =
        BufferedReader(InputStreamReader(Thread.currentThread().contextClassLoader.getResourceAsStream("valves.txt")))
    var line = reader.readLine()
    val closedPressureValves = mutableSetOf<String>()
    val valves = HashMap<String, Int>()
    val distances = HashMap<String, HashMap<String, Int>>()
    do {
        PATTERN.matcher(line!!).apply {
            find()
            val (name, pressure, leadsTo) = mutableListOf<String>().apply { repeat(3) { add(group(it + 1)) } }
            valves[name] = pressure.toInt()
            if (pressure.toInt() > 0) closedPressureValves.add(name)
            distances[name] = HashMap<String, Int>()
                .apply { leadsTo.split(", ").forEach { put(it, 1) } }
                .apply { put(name, 0) }
        }
        line = reader.readLine()
    } while (line != null)

    distances.forEach {(_, fromValve) ->
        val queue = LinkedList<Pair<String, Int>>().apply { fromValve.forEach {add(Pair(it.key, it.value))} }
        while (queue.isNotEmpty()){
            val (curValveName, distanceToCurValve) = queue.removeFirst()
            val fromCurValve = distances[curValveName]
            fromCurValve?.forEach {
                val newDistance = distanceToCurValve + it.value
                if (!fromValve.containsKey(it.key) || newDistance < fromValve[it.key]!!) {
                    fromValve[it.key] = newDistance
                    queue.add(Pair(it.key, newDistance))
                }
            }
        }
    }


    var timeStart = System.currentTimeMillis()
    var maxTotalPressure = 0
    lateinit var maxStateTrace: String
    val queue = LinkedList<ValveSystemState>()
    queue.add(ValveSystemState("AA", 30, 0, HashSet(closedPressureValves), trace = ""))
    do {
        val state = queue.removeFirst()
        val maxDeltaPressurePossible = state.closedValves.map(valves::get).map { it!! * (state.minute - 1) }.sum()
        if (maxDeltaPressurePossible + state.totalPressure <= maxTotalPressure) continue
        state.closedValves.forEach {
            val distance = distances[state.curValve]!![it]!!
            val newTotalPressure = (state.minute - distance - 1) * valves[it]!! + state.totalPressure
            val newTrace = state.trace + "${state.minute} Moving from ${state.curValve} to ${it} for ${distance} minutes and open it adding ${newTotalPressure - state.totalPressure}\r\n"
            if (newTotalPressure > maxTotalPressure) {
                maxTotalPressure = newTotalPressure
                maxStateTrace = newTrace
            }
            queue.add(
                ValveSystemState(
                    it,
                    state.minute - distance - 1,
                    newTotalPressure,
                    state.closedValves.filter { test -> it != test }.toSet(),
                    trace = newTrace
                )
            )
        }
    } while (!queue.isEmpty())
    println(closedPressureValves)
    println(maxTotalPressure)
    println(maxStateTrace!!)
    println(System.currentTimeMillis() - timeStart)
}
