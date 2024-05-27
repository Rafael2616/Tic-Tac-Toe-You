package com.rafael.tictactoe.feature.game.domain.board.levels

import com.rafael.tictactoe.feature.game.domain.model.GameState

/*
 * @author Rafael de Moura Nascimento in 2023/10/27
 *
 * Algorithm to execute the best move for "CPU player"
 * takes into account which player (real or machine) to block and
 * found the best way to win the game
 */

internal fun hardLevelCPUMove(state: GameState): Pair<Int, Int> {
    // Represents the clicked state of the card
    val isClicked = state.isCardClicked

    // Represent the player, if false is the player 1, if true the player 2
    val card = state.card

    val sides = listOf(Pair(0, 1), Pair(1, 0), Pair(1, 2), Pair(2, 1))
    val corners = listOf(Pair(0, 0), Pair(0, 2), Pair(2, 0), Pair(2, 2))
    val center = Pair(1, 1)

    // Choose the center if there are 3 corners clicked
    if (card[corners[0].first][corners[0].second] && card[corners[1].first][corners[1].second] && card[corners[2].first][corners[2].second]) {
        if (!isClicked[1][1]) {
            return Pair(1, 1)
        }
    }

    if (card[corners[3].first][corners[3].second] && card[corners[1].first][corners[1].second] && card[corners[2].first][corners[2].second]) {
        if (!isClicked[1][1]) {
            return Pair(1, 1)
        }
    }

    if (card[corners[3].first][corners[3].second] && card[corners[0].first][corners[0].second] && card[corners[2].first][corners[2].second]) {
        if (!isClicked[1][1]) {
            return Pair(1, 1)
        }
    }

    if (card[corners[3].first][corners[3].second] && card[corners[0].first][corners[0].second] && card[corners[1].first][corners[1].second]) {
        if (!isClicked[1][1]) {
            return Pair(1, 1)
        }
    }

    // Choose best side if two corners are clicked
    if ((card[corners[0].first][corners[0].second] && isClicked[corners[0].first][corners[0].second]) &&
        (card[corners[1].first][corners[1].second] && isClicked[corners[1].first][corners[1].second])
    ) {
        if (!isClicked[0][1]) {
            return Pair(0, 1)
        }
    } else if ((!card[corners[0].first][corners[0].second] && isClicked[corners[0].first][corners[0].second]) &&
        (!card[corners[1].first][corners[1].second] && isClicked[corners[1].first][corners[1].second])
    ) {
        if (!isClicked[0][1]) {
            return Pair(0, 1)
        }
    }

    if ((card[corners[0].first][corners[0].second] && isClicked[corners[0].first][corners[0].second]) &&
        (card[corners[2].first][corners[2].second] && isClicked[corners[2].first][corners[2].second])
    ) {
        if (!isClicked[1][0]) {
            return Pair(1, 0)
        }
    } else if ((!card[corners[0].first][corners[0].second] && isClicked[corners[0].first][corners[0].second]) &&
        (!card[corners[2].first][corners[2].second] && isClicked[corners[2].first][corners[2].second])
    ) {
        if (!isClicked[1][0]) {
            return Pair(1, 0)
        }
    }

    if ((card[corners[2].first][corners[2].second] && isClicked[corners[2].first][corners[2].second]) &&
        (card[corners[3].first][corners[3].second] && isClicked[corners[3].first][corners[3].second])
    ) {
        if (!isClicked[2][1]) {
            return Pair(2, 1)
        }
    } else if ((!card[corners[2].first][corners[2].second] && isClicked[corners[2].first][corners[2].second]) &&
        (!card[corners[3].first][corners[3].second] && isClicked[corners[3].first][corners[3].second])
    ) {
        if (!isClicked[2][1]) {
            return Pair(2, 1)
        }
    }

    if ((card[corners[1].first][corners[1].second] && isClicked[corners[1].first][corners[1].second]) &&
        (card[corners[3].first][corners[3].second] && isClicked[corners[3].first][corners[3].second])
    ) {
        if (!isClicked[1][2]) {
            return Pair(1, 2)
        }
    } else if ((!card[corners[1].first][corners[1].second] && isClicked[corners[1].first][corners[1].second]) &&
        (!card[corners[3].first][corners[3].second] && isClicked[corners[3].first][corners[3].second])
    ) {
        if (!isClicked[1][2]) {
            return Pair(1, 2)
        }
    }

    if ((card[corners[0].first][corners[0].second] && isClicked[corners[0].first][corners[0].second]) &&
        (card[corners[3].first][corners[3].second] && isClicked[corners[3].first][corners[3].second])
    ) {
        if (!isClicked[1][1]) {
            return Pair(1, 1)
        }
    } else if ((!card[corners[0].first][corners[0].second] && isClicked[corners[0].first][corners[0].second]) &&
        (!card[corners[3].first][corners[3].second] && isClicked[corners[3].first][corners[3].second])
    ) {
        if (!isClicked[1][1]) {
            return Pair(1, 1)
        }
    }

    if ((card[corners[1].first][corners[1].second] && isClicked[corners[1].first][corners[1].second]) &&
        (card[corners[2].first][corners[2].second] && isClicked[corners[2].first][corners[2].second])
    ) {
        if (!isClicked[1][1]) {
            return Pair(1, 1)
        }
    } else if ((!card[corners[1].first][corners[1].second] && isClicked[corners[1].first][corners[1].second]) &&
        (!card[corners[2].first][corners[2].second] && isClicked[corners[2].first][corners[2].second])
    ) {
        if (!isClicked[1][1]) {
            return Pair(1, 1)
        }
    }

    // Choose the best side based in which side is cliked when center [1][1] is already clicked too and which player has been clicked
    if ((card[center.first][center.second] && isClicked[center.first][center.second]) &&
        (card[sides[0].first][sides[0].second] && isClicked[sides[0].first][sides[0].second])
    ) {
        if (!isClicked[2][1]) {
            return Pair(2, 1)
        }
    } else if ((!card[center.first][center.second] && isClicked[center.first][center.second]) &&
        (!card[sides[0].first][sides[0].second] && isClicked[sides[0].first][sides[0].second])
    ) {
        if (!isClicked[2][1]) {
            return Pair(2, 1)
        }
    }

    if ((card[center.first][center.second] && isClicked[center.first][center.second]) &&
        (card[sides[1].first][sides[1].second] && isClicked[sides[1].first][sides[1].second])
    ) {
        if (!isClicked[1][2]) {
            return Pair(1, 2)
        }
    } else if ((!card[center.first][center.second] && isClicked[center.first][center.second]) &&
        (!card[sides[1].first][sides[1].second] && isClicked[sides[1].first][sides[1].second])
    ) {
        if (!isClicked[1][2]) {
            return Pair(1, 2)
        }
    }

    if ((card[center.first][center.second] && isClicked[center.first][center.second]) &&
        (card[sides[2].first][sides[2].second] && isClicked[sides[2].first][sides[2].second])
    ) {
        if (!isClicked[1][0]) {
            return Pair(1, 0)
        }
    } else if ((!card[center.first][center.second] && isClicked[center.first][center.second]) &&
        (!card[sides[2].first][sides[2].second] && isClicked[sides[2].first][sides[2].second])
    ) {
        if (!isClicked[1][0]) {
            return Pair(1, 0)
        }
    }

    if ((card[center.first][center.second] && isClicked[center.first][center.second]) &&
        (card[sides[3].first][sides[3].second] && isClicked[sides[3].first][sides[3].second])
    ) {
        if (!isClicked[0][1]) {
            return Pair(0, 1)
        }
    } else if ((!card[center.first][center.second] && isClicked[center.first][center.second]) &&
        (!card[sides[3].first][sides[3].second] && isClicked[sides[3].first][sides[3].second])
    ) {
        if (!isClicked[0][1]) {
            return Pair(0, 1)
        }
    }

    // Choose the best corner based in which corner is cliked when center [1][1] is already clicked too and which player has been clicked
    if ((card[center.first][center.second] && isClicked[center.first][center.second]) &&
        (card[corners[0].first][corners[0].second] && isClicked[corners[0].first][corners[0].second])
    ) {
        if (!isClicked[2][2]) {
            return Pair(2, 2)
        }
    } else if ((!card[center.first][center.second] && isClicked[center.first][center.second]) &&
        (!card[corners[0].first][corners[0].second] && isClicked[corners[0].first][corners[0].second])
    ) {
        if (!isClicked[2][2]) {
            return Pair(2, 2)
        }
    }

    if ((card[center.first][center.second] && isClicked[center.first][center.second]) &&
        (card[corners[1].first][corners[1].second] && isClicked[corners[1].first][corners[1].second])
    ) {
        if (!isClicked[2][0]) {
            return Pair(2, 0)
        }
    } else if ((!card[center.first][center.second] && isClicked[center.first][center.second]) &&
        (!card[corners[1].first][corners[1].second] && isClicked[corners[1].first][corners[1].second])
    ) {
        if (!isClicked[2][0]) {
            return Pair(2, 0)
        }
    }

    if ((card[center.first][center.second] && isClicked[center.first][center.second]) &&
        (card[corners[2].first][corners[2].second] && isClicked[corners[2].first][corners[2].second])
    ) {
        if (!isClicked[0][2]) {
            return Pair(0, 2)
        }
    } else if ((!card[center.first][center.second] && isClicked[center.first][center.second]) &&
        (!card[corners[2].first][corners[2].second] && isClicked[corners[2].first][corners[2].second])
    ) {
        if (!isClicked[0][2]) {
            return Pair(0, 2)
        }
    }

    if ((card[center.first][center.second] && isClicked[center.first][center.second]) &&
        (card[corners[3].first][corners[3].second] && isClicked[corners[3].first][corners[3].second])
    ) {
        if (!isClicked[0][0]) {
            return Pair(0, 0)
        }
    } else if ((!card[center.first][center.second] && isClicked[center.first][center.second]) &&
        (!card[corners[3].first][corners[3].second] && isClicked[corners[3].first][corners[3].second])
    ) {
        if (!isClicked[0][0]) {
            return Pair(0, 0)
        }
    }

    // Choose the best corner based in which side and corner is cliked and which player has been clicked
    if (
        (card[corners[0].first][corners[0].second] && isClicked[corners[0].first][corners[0].second]) &&
        (card[sides[0].first][sides[0].second] && isClicked[sides[0].first][sides[0].second]) ||
        (card[corners[3].first][corners[3].second] && isClicked[corners[3].first][corners[3].second]) &&
        (card[sides[2].first][sides[2].second] && isClicked[sides[2].first][sides[2].second])
    ) {
        if (!isClicked[0][2]) {
            return Pair(0, 2)
        }
    } else if (
        (!card[corners[0].first][corners[0].second] && isClicked[corners[0].first][corners[0].second]) &&
        (!card[sides[0].first][sides[0].second] && isClicked[sides[0].first][sides[0].second]) ||
        (!card[corners[3].first][corners[3].second] && isClicked[corners[3].first][corners[3].second]) &&
        (!card[sides[2].first][sides[2].second] && isClicked[sides[2].first][sides[2].second])
    ) {
        if (!isClicked[0][2]) {
            return Pair(0, 2)
        }
    }

    if (
        (card[corners[1].first][corners[1].second] && isClicked[corners[1].first][corners[1].second]) &&
        (card[sides[0].first][sides[0].second] && isClicked[sides[0].first][sides[0].second]) ||
        (card[corners[2].first][corners[2].second] && isClicked[corners[2].first][corners[2].second]) &&
        (card[sides[1].first][sides[1].second] && isClicked[sides[1].first][sides[1].second])
    ) {
        if (!isClicked[0][0]) {
            return Pair(0, 0)
        }
    } else if (
        (!card[corners[1].first][corners[1].second] && isClicked[corners[1].first][corners[1].second]) &&
        (!card[sides[0].first][sides[0].second] && isClicked[sides[0].first][sides[0].second]) ||
        (!card[corners[2].first][corners[2].second] && isClicked[corners[2].first][corners[2].second]) &&
        (!card[sides[1].first][sides[1].second] && isClicked[sides[1].first][sides[1].second])
    ) {
        if (!isClicked[0][0]) {
            return Pair(0, 0)
        }
    }

    if (
        (card[corners[2].first][corners[2].second] && isClicked[corners[2].first][corners[2].second]) &&
        (card[sides[3].first][sides[3].second] && isClicked[sides[3].first][sides[3].second]) ||
        (card[corners[1].first][corners[1].second] && isClicked[corners[1].first][corners[1].second]) &&
        (card[sides[2].first][sides[2].second] && isClicked[sides[2].first][sides[2].second])
    ) {
        if (!isClicked[2][2]) {
            return Pair(2, 2)
        }
    } else if (
        (!card[corners[2].first][corners[2].second] && isClicked[corners[2].first][corners[2].second]) &&
        (!card[sides[3].first][sides[3].second] && isClicked[sides[3].first][sides[3].second]) ||
        (!card[corners[1].first][corners[1].second] && isClicked[corners[1].first][corners[1].second]) &&
        (!card[sides[2].first][sides[2].second] && isClicked[sides[2].first][sides[2].second])
    ) {
        if (!isClicked[2][2]) {
            return Pair(2, 2)
        }
    }

    if (
        (card[corners[0].first][corners[0].second] && isClicked[corners[0].first][corners[0].second]) &&
        (card[sides[1].first][sides[1].second] && isClicked[sides[1].first][sides[1].second]) ||
        (card[corners[3].first][corners[3].second] && isClicked[corners[3].first][corners[3].second]) &&
        (card[sides[3].first][sides[3].second] && isClicked[sides[3].first][sides[3].second])
    ) {
        if (!isClicked[2][0]) {
            return Pair(2, 0)
        }
    } else if (
        (!card[corners[0].first][corners[0].second] && isClicked[corners[0].first][corners[0].second]) &&
        (!card[sides[1].first][sides[1].second] && isClicked[sides[1].first][sides[1].second]) ||
        (!card[corners[3].first][corners[3].second] && isClicked[corners[3].first][corners[3].second]) &&
        (!card[sides[3].first][sides[3].second] && isClicked[sides[3].first][sides[3].second])
    ) {
        if (!isClicked[2][0]) {
            return Pair(2, 0)
        }
    }

    // Choose an corner if center [1][1] and other corner is clicked too and which player has been clicked
    val randomCorners = corners.shuffled()
    for (corner in randomCorners) {
        if (card[corner.first][corner.second] && card[1][1]) {
            when (corner) {
                Pair(0, 0) -> {
                    if (!isClicked[2][2])return Pair(2, 2)
                }
                Pair(0, 2) -> {
                    if (!isClicked[2][0])return Pair(2, 0)
                }
                Pair(2, 0) -> {
                    if (!isClicked[0][2])return Pair(0, 2)
                }
                Pair(2, 2) -> {
                    if (!isClicked[0][0])return Pair(0, 0)
                }
            }
        }
    }

    // Choose an card based on which corner is already clicked
    // This choice is made with a random variable, based on the possible possibilities for each case

    val strategy = (0 until 5).random()

    for (corner in randomCorners) {
        if (isClicked[corner.first][corner.second]) {
            when (corner) {
                Pair(0, 0) -> {
                    if (!isClicked[1][0] && strategy == 0) {
                        return Pair(1, 0)
                    }
                    if (!isClicked[2][0] && strategy == 1) {
                        return Pair(2, 0)
                    }
                    if (!isClicked[0][1] && strategy == 2) {
                        return Pair(0, 1)
                    }
                    if (!isClicked[0][2] && strategy == 3) {
                        return Pair(0, 2)
                    }
                    if (!isClicked[2][2] && strategy == 4) {
                        return Pair(2, 2)
                    }
                }
                Pair(0, 2) -> {
                    if (!isClicked[0][0] && strategy == 0) {
                        return Pair(0, 0)
                    }
                    if (!isClicked[0][1] && strategy == 1) {
                        return Pair(0, 1)
                    }
                    if (!isClicked[2][0] && strategy == 2) {
                        return Pair(2, 0)
                    }
                    if (!isClicked[1][2] && strategy == 3) {
                        return Pair(1, 2)
                    }
                    if (!isClicked[2][2] && strategy == 4) {
                        return Pair(2, 2)
                    }
                }
                Pair(2, 0) -> {
                    if (!isClicked[0][0] && strategy == 0) {
                        return Pair(0, 0)
                    }
                    if (!isClicked[1][0] && strategy == 1) {
                        return Pair(1, 0)
                    }
                    if (!isClicked[0][2] && strategy == 2) {
                        return Pair(0, 2)
                    }
                    if (!isClicked[2][1] && strategy == 3) {
                        return Pair(2, 1)
                    }
                    if (!isClicked[2][2] && strategy == 4) {
                        return Pair(2, 2)
                    }
                }
                Pair(2, 2) -> {
                    if (!isClicked[0][0] && strategy == 0) {
                        return Pair(0, 0)
                    }
                    if (!isClicked[2][0] && strategy == 1) {
                        return Pair(2, 0)
                    }
                    if (!isClicked[2][1] && strategy == 2) {
                        return Pair(2, 1)
                    }
                    if (!isClicked[0][2] && strategy == 3) {
                        return Pair(0, 2)
                    }
                    if (!isClicked[1][2] && strategy == 4) {
                        return Pair(1, 2)
                    }
                }
            }
        }

        // Choose center, if not clicked
        // the choose is not performed always, but with an probability
        // to make the game more human

        val useCenterStrategy = (0 until 3).random()

        if (!isClicked[1][1] && (useCenterStrategy == 0 || useCenterStrategy == 1)) {
            return Pair(1, 1)
        }

        if (!isClicked[corner.first][corner.second]) {
            return corner
        }
    }

    // Choose an side, if is not clicked yet
    val side = sides.random()
    if (!isClicked[side.first][side.second]) {
        return side
    }

    // Returns any value if all strategies above fail
    for (i in 0 until 3) {
        for (j in 0 until 3) {
            if (!isClicked[i][j]) {
                return Pair(i, j)
            }
        }
    }

    // Shoudn't reach here
    return Pair(-1, -1)
}
