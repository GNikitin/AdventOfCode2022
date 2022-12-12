package me.nikigle.aoc.day11


fun main(args: Array<String>) {
    val monkeys = ArrayList<Monkey>(8)
    initMonkeys(monkeys)
    val monkeyInspections = IntArray(monkeys.size) { 0 }
    repeat(20) {
        monkeys.forEachIndexed { i, monkey ->
            monkey.apply {
                while(items.isNotEmpty()) {
                    var wl = items.removeFirst()
                    monkeyInspections[i]++
                    val newWorryLevel = changeWorryLevel(wl).toInt() / 3
                    if (newWorryLevel % testDivider == 0) monkeys[testTrueNext].items.add(newWorryLevel)
                    else monkeys[testFalseNext].items.add(newWorryLevel)
                }
            }
        }
    }
    monkeyInspections.sort()
    print(monkeyInspections.last().toLong() * monkeyInspections.let { it[it.size - 2] }.toLong())
}

fun initMonkeys(monkeys: MutableList<Monkey>) {
    monkeys += Monkey(19, 5, 3) { it * 17L }
    monkeys[0].items.addAll(listOf(93, 98))
    monkeys += Monkey(13, 7, 6) { it + 5L }
    monkeys[1].items.addAll(listOf(95, 72, 98, 82, 86))
    monkeys += Monkey(5, 3, 0) { it + 8L }
    monkeys[2].items.addAll(listOf(85, 62, 82, 86, 70, 65, 83, 76))
    monkeys += Monkey(7, 4, 5) { it + 1L }
    monkeys[3].items.addAll(listOf(86, 70, 71, 56))
    monkeys += Monkey(17, 1, 6) { it + 4L }
    monkeys[4].items.addAll(listOf(77, 71, 86, 52, 81, 67))
    monkeys += Monkey(2, 1, 4) { it * 7L }
    monkeys[5].items.addAll(listOf(89, 87, 60, 78, 54, 77, 98))
    monkeys += Monkey(3, 7, 2) { it + 6L }
    monkeys[6].items.addAll(listOf(69, 65, 63))
    monkeys += Monkey(11, 0, 2) { it * it.toLong() }
    monkeys[7].items.addAll(listOf(89))
}

//fun initMonkeys(monkeys: MutableList<Monkey>) {
//    monkeys += Monkey(23, 2, 3) { it * 19L }
//    monkeys[0].items.addAll(listOf(79, 98))
//    monkeys += Monkey(19, 2, 0) { it + 6L }
//    monkeys[1].items.addAll(listOf(54, 65, 75, 74))
//    monkeys += Monkey(13, 1, 3) { it * it.toLong() }
//    monkeys[2].items.addAll(listOf(79, 60, 97))
//    monkeys += Monkey(17, 0, 1) { it + 3L }
//    monkeys[3].items.addAll(listOf(74))
//}