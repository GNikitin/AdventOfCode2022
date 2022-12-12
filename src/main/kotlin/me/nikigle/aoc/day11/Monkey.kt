package me.nikigle.aoc.day11

import java.math.BigInteger
import java.util.*

class Monkey(
    val testDivider: Int,
    val testTrueNext: Int,
    val testFalseNext: Int,
    val changeWorryLevel: (Int) -> Long

) {
    val items = LinkedList<Int>()
}