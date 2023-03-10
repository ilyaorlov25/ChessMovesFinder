package com.example.plugins

import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*

const val CELL = "cell"
val pattern = Regex("""[a-h][1-8]""")

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello! Type a figure (king, rook, bishop, knight) and a cell")
        }

        get("/king") {
            val cell = call.parameters[CELL]
            if (cell == null) {
                call.respondText("Empty parameter")
            } else if (!cell.matches(pattern)) {
                call.respondText("Incorrect cell")
            } else {
                call.respondText(printResult(Figure.KING, cell))
            }
        }

        get("/rook") {
            val cell = call.parameters[CELL]
            if (cell == null) {
                call.respondText("Empty parameter")
            } else if (!cell.matches(pattern)) {
                call.respondText("Incorrect cell")
            } else {
                call.respondText(printResult(Figure.ROOK, cell))
            }
        }

        get("/bishop") {
            val cell = call.parameters[CELL]
            if (cell == null) {
                call.respondText("Empty parameter")
            } else if (!cell.matches(pattern)) {
                call.respondText("Incorrect cell")
            } else {
                call.respondText(printResult(Figure.BISHOP, cell))
            }
        }

        get("/knight") {
            val cell = call.parameters[CELL]
            if (cell == null) {
                call.respondText("Empty parameter")
            } else if (!cell.matches(pattern)) {
                call.respondText("Incorrect cell")
            } else {
                call.respondText(printResult(Figure.KNIGHT, cell))
            }
        }
    }
}

fun printResult(figure: Figure, cell: String): String {
    val column = columnsToInt[cell[0]]!!
    val row = cell[1].toString().toInt()
    val possibleMoves = checkMoves(figure, column, row)
    val answer = StringBuilder()
    for (move in possibleMoves) {
        answer.append("${columnsToString[move.first]}${move.second}\n")
    }
    return "Possible moves: \n$answer"
}


enum class Figure {
    KING,
    ROOK,
    BISHOP,
    KNIGHT
}

val columnsToInt = mapOf(
    'a' to 1,
    'b' to 2,
    'c' to 3,
    'd' to 4,
    'e' to 5,
    'f' to 6,
    'g' to 7,
    'h' to 8
)

val columnsToString = mapOf(
    1 to 'a',
    2 to 'b',
    3 to 'c',
    4 to 'd',
    5 to 'e',
    6 to 'f',
    7 to 'g',
    8 to 'h'
)

fun checkMoves(figure: Figure, column: Int, row: Int): List<Pair<Int, Int>> {
    val possibleMoves = mutableSetOf<Pair<Int, Int>>()
    when(figure) {
        Figure.KING -> {
            for (i in column - 1..column + 1) {
                for (j in row - 1..row + 1) {
                    possibleMoves.add(Pair(i, j))
                }
            }
        }
        Figure.ROOK -> {
            for (i in 1..8) {
                possibleMoves.add(Pair(i, row))
                possibleMoves.add(Pair(column, i))
            }
        }
        Figure.BISHOP -> {
            val difference = row - column
            val sum = row + column
            for (i in 1..8) {
                possibleMoves.add(Pair(i - difference, i))
                possibleMoves.add(Pair(i, sum - i))
            }
        }
        Figure.KNIGHT -> {
            possibleMoves.add(Pair(column - 2, row + 1))
            possibleMoves.add(Pair(column - 2, row - 1))
            possibleMoves.add(Pair(column - 1, row + 2))
            possibleMoves.add(Pair(column - 1, row - 2))
            possibleMoves.add(Pair(column + 1, row + 2))
            possibleMoves.add(Pair(column + 1, row - 2))
            possibleMoves.add(Pair(column + 2, row + 1))
            possibleMoves.add(Pair(column + 2, row - 1))
        }
    }
    possibleMoves.removeIf {
        it.first == column && it.second == row
    }
    return possibleMoves.filter {
        it.first in 1..8 && it.second in 1..8
    }
}