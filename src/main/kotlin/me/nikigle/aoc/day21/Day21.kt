package me.nikigle.aoc.day21

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedList

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

    println(calculate(monkeyOperations, "root"))
}

fun calculate(monkeyOperations: HashMap<String, String>, root: String): Long {
    val operands = LinkedList<String>()
    val operators = LinkedList<Char>()
    operands.add(root)
    do {
        val firstOperand = operands.pop()
        if (firstOperand.toLongOrNull() == null && monkeyOperations[firstOperand]!!.toLongOrNull() == null) {
            val resolvingOperation = monkeyOperations[firstOperand]!!
            operands.push(resolvingOperation.substring(0, 4))
            operators.push(resolvingOperation[5])
            operands.push(resolvingOperation.substring(7, 11))
            continue
        }
        val firstOperandInt = firstOperand.toLongOrNull() ?: monkeyOperations[firstOperand]!!.toLongOrNull()!!
        if (operators.isEmpty()) {
            operands.push(firstOperandInt.toString())
            continue
        }
        val operator = operators.pop()
        val secondOperand = operands.pop()
        if (secondOperand.toLongOrNull() == null && monkeyOperations[secondOperand]!!.toLongOrNull() == null) {
            when (operator) {
                '/' -> operators.push('%')
                '%' -> operators.push('/')
                '-' -> operators.push('!')
                '!' -> operators.push('-')
                else -> operators.push(operator)
            }
            operands.push(firstOperand)
            val resolvingOperation = monkeyOperations[secondOperand]!!
            operands.push(resolvingOperation.substring(0, 4))
            operators.push(resolvingOperation[5])
            operands.push(resolvingOperation.substring(7, 11))
            continue
        }


        val secondOperandInt = secondOperand.toLongOrNull() ?: monkeyOperations[secondOperand]!!.toLongOrNull()!!
        when (operator) {
            '*' -> operands.push((firstOperandInt * secondOperandInt).toString())
            '+' -> operands.push((firstOperandInt + secondOperandInt).toString())
            '!' -> operands.push((firstOperandInt - secondOperandInt).toString())
            '-' -> operands.push((secondOperandInt - firstOperandInt).toString())
            '/' -> operands.push((secondOperandInt / firstOperandInt).toString())
            '%' -> operands.push((firstOperandInt / secondOperandInt).toString())
        }
    } while (!operators.isEmpty())
    return operands.pop().toLong()
}