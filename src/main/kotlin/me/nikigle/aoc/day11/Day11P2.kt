package me.nikigle.aoc.day11


fun main(args: Array<String>) {
    val monkeys = ArrayList<Monkey>(8)
    initMonkeys(monkeys)
    val monkeyInspections = IntArray(monkeys.size) { 0 }
    val modulo = monkeys.map { it.testDivider }.reduce( Int::times )
    repeat(10000) {
        monkeys.forEachIndexed { i, monkey ->
            monkey.apply {
                while(items.isNotEmpty()) {
                    val wl = items.removeFirst()
                    monkeyInspections[i]++
                    val newWorryLevel = (changeWorryLevel(wl) % modulo).toInt()
                    if (newWorryLevel % testDivider == 0) monkeys[testTrueNext].items.add(newWorryLevel)
                    else monkeys[testFalseNext].items.add(newWorryLevel)
                }
            }
        }
    }
    monkeyInspections.sort()
    print(monkeyInspections.last().toLong() * monkeyInspections.let { it[it.size - 2] })
}

