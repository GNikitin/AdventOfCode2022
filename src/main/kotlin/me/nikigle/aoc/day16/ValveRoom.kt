package me.nikigle.aoc.day16

import java.util.regex.Pattern

val PATTERN = Pattern.compile("Valve (..) has flow rate=(\\d+); tunnels? leads? to valves? (.*)")

data class ValveSystemState (
    val curValve: String,
    var minute: Int,
    val totalPressure: Int,
    val closedValves: Set<String>,
    val trace: String? = null
) {
}
