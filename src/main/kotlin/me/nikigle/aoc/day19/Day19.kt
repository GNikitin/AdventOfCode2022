package me.nikigle.aoc.day19

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedList
import kotlin.math.max

fun main() {
    val reader =
        BufferedReader(InputStreamReader(Thread.currentThread().contextClassLoader.getResourceAsStream("blueprints.txt")))
    var line = reader.readLine()
    val factories = HashMap<Int, RobotFactory>()
    do {
        PATTERN.matcher(line!!).apply {
            find()
            factories[group(1).toInt()] = RobotFactory(
                group(2).toInt(),
                group(3).toInt(),
                group(4).toInt(),
                group(5).toInt(),
                group(6).toInt(),
                group(7).toInt()
            )
        }
        line = reader.readLine()
    } while (line != null)

    var qualityLevelSum = 0
    factories.forEach {
        println("Calculating ${it.key}")
        val factory = it.value
        var maxGeodes = 0
        var stack = LinkedList<State>()
        stack.add(State(Resources(), Resources(1), 24))
        while (!stack.isEmpty()) {
            val state = stack.removeLast()

            if (maxGeodes > state.resources.geode + state.minutesLeft * state.income.geode + (state.minutesLeft * (state.minutesLeft + 1)) / 2) {
                continue
            }

            buildOreRobotState(factory, state)?.let { s -> stack.add(s) }
            buildClayRobotState(factory, state)?.let { it1 -> stack.add(it1) }

            if (state.income.clay > 0) {
                buildObsidianRobotState(factory, state)?.let { it1 -> stack.add(it1) }
            }
            if (state.income.obsidian > 0) {
                buildGeodeRobotState(factory, state)?.let { it1 -> stack.add(it1) }
            }
            maxGeodes = max(maxGeodes, state.resources.geode + state.minutesLeft * state.income.geode)
        }
        println("Adding $maxGeodes * ${it.key} = ${maxGeodes * it.key}")
        qualityLevelSum += maxGeodes * it.key
    }
    println(qualityLevelSum)
}
