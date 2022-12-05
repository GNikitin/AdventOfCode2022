package me.nikigle.aoc;

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.Stack
import java.util.regex.Pattern

fun main(args: Array<String>) {
    val pattern = Pattern.compile("move (.*) from (.*) to (.*)")
    val stacks = initStacks()
    val reader =
        BufferedReader(InputStreamReader(Thread.currentThread().contextClassLoader.getResourceAsStream("operations.txt")))
    var line = reader.readLine()

    do {
        line!!
        val matcher = pattern.matcher(line)
        matcher.find()
        val times = matcher.group(1).toInt()
        val from = matcher.group(2).toInt()
        val to = matcher.group(3).toInt()
        val tempStack = Stack<Char>()
        for (i in 1..times) {
            tempStack.push(stacks.get(from)!!.pop())
        }
        for(i in 1..times) {
            stacks.get(to)!!.push(tempStack.pop())
        }
        line = reader.readLine()
    } while (line != null)

    var result = ""
    for (stack in stacks) {
        result += stack.value.peek()
    }
    println(result)
}
private fun initStacks(): Map<Int, Stack<Char>> {
    val result: MutableMap<Int, Stack<Char>> = LinkedHashMap()
    var array: Array<String> = Array(10) { i ->
        when (i) {
            1 -> "NSDCVQT"
            2 -> "MFV"
            3 -> "FQWDPNHM"
            4 -> "DQRTF"
            5 -> "RFMNQHVB"
            6 -> "CFGNPWQ"
            7 -> "WFRLCT"
            8 -> "TZNS"
            else -> "MSDJRQHN"
        }
    }
    for (i in 1..array.size - 1) {
        var stack = Stack<Char>()
        array[i].toCharArray().forEach(stack::push)
        result.put(i, stack)
    }
    return result
}
