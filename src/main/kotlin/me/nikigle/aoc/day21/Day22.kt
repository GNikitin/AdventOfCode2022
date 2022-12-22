package me.nikigle.aoc.day21

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedList
import java.util.regex.Pattern

val PATTERN = Pattern.compile("(....) (.) (....)")
fun main() {
    val reader =
        BufferedReader(InputStreamReader(Thread.currentThread().contextClassLoader.getResourceAsStream("monkeys-math.txt")))
    var line = reader.readLine()

    val monkeyOperations = HashMap<String, String>()
    do {
        val (name, operation) = line!!.split(": ")
        monkeyOperations[name] = operation
        line = reader.readLine()
    } while (line != null)

    var humanChain = resolveHumanChain(monkeyOperations, "root")
    humanChain.removeFirst()
    var matcher = PATTERN.matcher(monkeyOperations["root"]!!)
    matcher.find()
    var firstMonkey = matcher.group(1)
    var humanLeadingMonkey = humanChain.removeFirst()
    var calculateNumberMonkey = if (humanLeadingMonkey == firstMonkey) matcher.group(3) else firstMonkey
    var result = calculate(monkeyOperations, calculateNumberMonkey)
    while (!humanChain.isEmpty()) {
        val operationString = monkeyOperations[humanLeadingMonkey]
        matcher = PATTERN.matcher(operationString!!)
        matcher.find()
        humanLeadingMonkey = humanChain.removeFirst()
        if (humanLeadingMonkey == "humn") {
            println("humn found")
        }
        println("Result of $operationString = $result, to human - $humanLeadingMonkey")
        firstMonkey = matcher.group(1)
        calculateNumberMonkey = if (humanLeadingMonkey == firstMonkey) matcher.group(3) else firstMonkey
        val monkeyResult = calculate(monkeyOperations, calculateNumberMonkey)
        when (matcher.group(2)[0]) {
            '+' -> result -= monkeyResult
            '*' -> result /= monkeyResult
            '/' -> result = if (firstMonkey == calculateNumberMonkey) monkeyResult / result else monkeyResult * result
            '-' -> result = if (firstMonkey == calculateNumberMonkey) monkeyResult - result else monkeyResult + result
        }
    }

    println(result)
}

fun resolveHumanChain(monkeyOperations: HashMap<String, String>, root: String): LinkedList<String> {
    if (root == "humn") return LinkedList<String>().apply { add("humn") }
    val children = monkeyOperations[root]
    val matcher = PATTERN.matcher(children!!)
    if (!matcher.find()) {
        return LinkedList()
    }
    val result = LinkedList<String>().apply {
        val firstChain = resolveHumanChain(monkeyOperations, matcher.group(1))
        if (!firstChain.isEmpty()) {
            add(root)
            addAll(firstChain)
        } else {
            val secondChain = resolveHumanChain(monkeyOperations, matcher.group(3))
            if (!secondChain.isEmpty()) {
                add(root)
                addAll(secondChain)
            }
        }
    }
    return result
}

