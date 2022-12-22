package me.nikigle.aoc.day19

import java.util.regex.Pattern
import kotlin.math.max

val PATTERN = Pattern.compile(
    "Blueprint (\\d+): Each ore robot costs (\\d+) ore\\. " +
            "Each clay robot costs (\\d+) ore\\. " +
            "Each obsidian robot costs (\\d+) ore and (\\d+) clay\\. " +
            "Each geode robot costs (\\d+) ore and (\\d+) obsidian\\."
)

data class RobotFactory(
    val oreRobotCost: Int,
    val clayRobotCost: Int,
    val obsidianRobotOreCost: Int,
    val obsidianRobotClayCost: Int,
    val geodeRobotOreCost: Int,
    val geodeRobotObsCost: Int,
)

data class Resources(
    val ore: Int = 0,
    val clay: Int = 0,
    val obsidian: Int = 0,
    val geode: Int = 0
)

data class State(
    val resources: Resources,
    val income: Resources,
    val minutesLeft: Int
)

fun buildOreRobotState(factory: RobotFactory, state: State): State? {
    var minutesToBuild = 1
    if (factory.oreRobotCost > state.resources.ore) {
        minutesToBuild += (factory.oreRobotCost - state.resources.ore) / state.income.ore
        if ((factory.oreRobotCost - state.resources.ore) % state.income.ore > 0) minutesToBuild++
    }

    if (minutesToBuild > state.minutesLeft - 2) {
        return null
    }

    return State(
        Resources(
            state.resources.ore + minutesToBuild * state.income.ore - factory.oreRobotCost,
            state.resources.clay + state.income.clay * minutesToBuild,
            state.resources.obsidian + state.income.obsidian * minutesToBuild,
            state.resources.geode + state.income.geode * minutesToBuild
        ),
        Resources(state.income.ore + 1, state.income.clay, state.income.obsidian, state.income.geode),
        state.minutesLeft - minutesToBuild
    )
}

fun buildClayRobotState(factory: RobotFactory, state: State): State? {
    var minutesToBuild = 1
    if (factory.clayRobotCost > state.resources.ore) {
        minutesToBuild += (factory.clayRobotCost - state.resources.ore) / state.income.ore
        if ((factory.clayRobotCost - state.resources.ore) % state.income.ore > 0) minutesToBuild++
    }

    if (minutesToBuild > state.minutesLeft - 2) {
        return null
    }

    return State(
        Resources(
            state.resources.ore + minutesToBuild * state.income.ore - factory.clayRobotCost,
            state.resources.clay + state.income.clay * minutesToBuild,
            state.resources.obsidian + state.income.obsidian * minutesToBuild,
            state.resources.geode + state.income.geode * minutesToBuild
        ),
        Resources(state.income.ore, state.income.clay + 1, state.income.obsidian, state.income.geode),
        state.minutesLeft - minutesToBuild
    )
}

fun buildObsidianRobotState(factory: RobotFactory, state: State): State? {
    var minutesToBuild = 1
    if (factory.obsidianRobotClayCost > state.resources.clay) {
        minutesToBuild += (factory.obsidianRobotClayCost - state.resources.clay) / state.income.clay
        if ((factory.obsidianRobotClayCost - state.resources.clay) % state.income.clay > 0) minutesToBuild++
    }

    if (factory.obsidianRobotOreCost > state.resources.ore) {
        var minutesToBuildOre = 1
        minutesToBuildOre += (factory.obsidianRobotOreCost - state.resources.ore) / state.income.ore
        if ((factory.obsidianRobotOreCost - state.resources.ore) % state.income.ore > 0) minutesToBuildOre++
        minutesToBuild = max(minutesToBuild, minutesToBuildOre)
    }


    if (minutesToBuild > state.minutesLeft - 1) {
        return null
    }

    return State(
        Resources(
            state.resources.ore + minutesToBuild * state.income.ore - factory.obsidianRobotOreCost,
            state.resources.clay + state.income.clay * minutesToBuild - factory.obsidianRobotClayCost,
            state.resources.obsidian + state.income.obsidian * minutesToBuild,
            state.resources.geode + state.income.geode * minutesToBuild
        ),
        Resources(state.income.ore, state.income.clay, state.income.obsidian + 1, state.income.geode),
        state.minutesLeft - minutesToBuild
    )
}

fun buildGeodeRobotState(factory: RobotFactory, state: State): State? {
    var minutesToBuild = 1
    if (factory.geodeRobotObsCost > state.resources.obsidian) {
        minutesToBuild += (factory.geodeRobotObsCost - state.resources.obsidian) / state.income.obsidian
        if ((factory.geodeRobotObsCost - state.resources.obsidian) % state.income.obsidian > 0) minutesToBuild++
    }

    if (factory.geodeRobotOreCost > state.resources.ore) {
        var minutesToBuildOre = 1
        minutesToBuildOre += (factory.geodeRobotOreCost - state.resources.ore) / state.income.ore
        if ((factory.geodeRobotOreCost - state.resources.ore) % state.income.ore > 0) minutesToBuildOre++
        minutesToBuild = max(minutesToBuild, minutesToBuildOre)
    }


    if (minutesToBuild > state.minutesLeft - 1) {
        return null
    }

    return State(
        Resources(
            state.resources.ore + minutesToBuild * state.income.ore - factory.geodeRobotOreCost,
            state.resources.clay + state.income.clay * minutesToBuild,
            state.resources.obsidian + state.income.obsidian * minutesToBuild - factory.geodeRobotObsCost,
            state.resources.geode + state.income.geode * minutesToBuild
        ),
        Resources(state.income.ore, state.income.clay, state.income.obsidian, state.income.geode + 1),
        state.minutesLeft - minutesToBuild
    )
}