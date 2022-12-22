package me.nikigle.aoc.day20

data class MovingElement(
    val value: Int,
    var moved: Boolean = false
)

data class MovingLongElement(
    val value: Long,
    var id: Int
)