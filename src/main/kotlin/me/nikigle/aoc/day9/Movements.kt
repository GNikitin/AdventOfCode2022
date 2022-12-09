package me.nikigle.aoc.day9

fun moveDown(ts: TailState): String? {
    when (ts.relativePos) {
        0 -> ts.relativePos = 12
        12 -> {
            ts.curTailY -= 1
            return "D 1"
        }

        1 -> {
            ts.curTailX -= 1
            ts.curTailY -= 1
            ts.relativePos = 12
            return "LD 1"
        }

        3 -> ts.relativePos = 1
        5 -> ts.relativePos = 3
        6 -> ts.relativePos = 0
        7 -> ts.relativePos = 9
        9 -> ts.relativePos = 11
        11 -> {
            ts.curTailX += 1
            ts.curTailY -= 1
            ts.relativePos = 12
            return "RD 1"
        }
    }
    return null
}

fun moveLeft(ts: TailState): String? {
    when (ts.relativePos) {
        0 -> ts.relativePos = 3
        12 -> ts.relativePos = 1
        1 -> {
            ts.curTailX -= 1
            ts.curTailY -= 1
            ts.relativePos = 3
            return "LD 1"
        }

        3 -> {
            ts.curTailX -= 1
            return "L 1"
        }

        5 -> {
            ts.curTailX -= 1
            ts.curTailY += 1
            ts.relativePos = 3
            return "LU 1"
        }

        6 -> ts.relativePos = 5
        7 -> ts.relativePos = 6
        9 -> ts.relativePos = 0
        11 -> ts.relativePos = 12
    }
    return null
}

fun moveRight(ts: TailState): String? {
    when (ts.relativePos) {
        0 -> ts.relativePos = 9
        12 -> ts.relativePos = 11
        1 -> ts.relativePos = 12
        3 -> ts.relativePos = 0
        5 -> ts.relativePos = 6
        6 -> ts.relativePos = 7
        7 -> {
            ts.curTailX += 1
            ts.curTailY += 1
            ts.relativePos = 9
            return "RU 1"
        }

        9 -> {
            ts.curTailX += 1
            return "R 1"
        }

        11 -> {
            ts.curTailX += 1
            ts.curTailY -= 1
            ts.relativePos = 9
            return "RD 1"
        }
    }
    return null
}

fun moveUp(ts: TailState): String? {
    when (ts.relativePos) {
        0 -> ts.relativePos = 6
        12 -> ts.relativePos = 0
        1 -> ts.relativePos = 3
        3 -> ts.relativePos = 5
        5 -> {
            ts.curTailX -= 1
            ts.curTailY += 1
            ts.relativePos = 6
            return "LU 1"
        }

        6 -> {
            ts.curTailY += 1
            return "U 1"
        }

        7 -> {
            ts.curTailX += 1
            ts.curTailY += 1
            ts.relativePos = 6
            return "RU 1"
        }

        9 -> ts.relativePos = 7
        11 -> ts.relativePos = 9
    }
    return null
}

fun moveRightUp(ts: TailState): String? {
    when (ts.relativePos) {
        0 -> ts.relativePos = 7
        12 -> ts.relativePos = 9
        1 -> ts.relativePos = 0
        3 -> ts.relativePos = 6
        5 -> {
            ts.curTailY += 1
            ts.relativePos = 6
            return "U 1"
        }

        6 -> {
            ts.curTailX += 1
            ts.curTailY += 1
            return "RU 1"
        }

        7 -> {
            ts.curTailX += 1
            ts.curTailY += 1
            return "RU 1"
        }

        9 -> {
            ts.curTailX += 1
            ts.curTailY += 1
            return "RU 1"
        }

        11 -> {
            ts.curTailX += 1
            ts.relativePos = 9
            return "R 1"
        }
    }
    return null
}

fun moveRightDown(ts: TailState): String? {
    when (ts.relativePos) {
        0 -> ts.relativePos = 11
        12 -> {
            ts.curTailY -= 1
            ts.curTailX += 1
            return "RD 1"
        }

        1 -> {
            ts.curTailY -= 1
            ts.relativePos = 12
            return "D 1"
        }

        3 -> ts.relativePos = 12
        5 -> ts.relativePos = 0
        6 -> ts.relativePos = 9
        7 -> {
            ts.curTailX += 1
            ts.relativePos = 9
            return "R 1"
        }

        9 -> {
            ts.curTailX += 1
            ts.curTailY -= 1
            return "RD 1"
        }

        11 -> {
            ts.curTailX += 1
            ts.curTailY -= 1
            return "RD 1"
        }
    }
    return null
}

fun moveLeftUp(ts: TailState): String? {
    when (ts.relativePos) {
        0 -> ts.relativePos = 5
        12 -> ts.relativePos = 3
        1 -> {
            ts.curTailX -= 1
            ts.relativePos = 3
            return "L 1"
        }

        3 -> {
            ts.curTailX -= 1
            ts.curTailY += 1
            return "LU 1"
        }

        5 -> {
            ts.curTailX -= 1
            ts.curTailY += 1
            return "LU 1"
        }

        6 -> {
            ts.curTailX -= 1
            ts.curTailY += 1
            return "LU 1"
        }

        7 -> {
            ts.curTailY += 1
            ts.relativePos = 6
            return "U 1"
        }

        9 -> ts.relativePos = 6
        11 -> ts.relativePos = 0
    }
    return null
}

fun moveLeftDown(ts: TailState): String? {
    when (ts.relativePos) {
        0 -> ts.relativePos = 1
        12 -> {
            ts.curTailX -= 1
            ts.curTailY -= 1
            return "LD 1"
        }

        1 -> {
            ts.curTailX -= 1
            ts.curTailY -= 1
            return "LD 1"
        }

        3 -> {
            ts.curTailX -= 1
            ts.curTailY -= 1
            return "LD 1"
        }

        5 -> {
            ts.curTailX -= 1
            ts.relativePos = 3
            return "L 1"
        }

        6 -> ts.relativePos = 3
        7 -> ts.relativePos = 0
        9 -> ts.relativePos = 12
        11 -> {
            ts.curTailY -= 1
            ts.relativePos = 12
            return "D 1"
        }
    }
    return null
}