import pt.isel.canvas.*

const val SPRITE_WIDTH = 40
const val SPRITE_HEIGHT = 54
const val GRID_WIDTH = 8
const val GRID_HEIGHT = 8

fun main() {
    onStart {
        val arena = Canvas(SPRITE_WIDTH * GRID_WIDTH, SPRITE_HEIGHT * GRID_HEIGHT, WHITE)
        var game = createGame()
        arena.drawGame(game)

        arena.onKeyPressed {
            game = game.collisionBoxes().move(it.code)
            arena.drawGame(game)

            if (!collisionTargets(game.boxes, game.targets)) {
                when (it.code) {

                    UP_CODE -> {
                        game = game.moveMan(Direction.UP)
                        arena.drawGame(game)

                    }

                    RIGHT_CODE -> {
                        game = game.moveMan(Direction.RIGHT)
                        arena.drawGame(game)

                    }

                    LEFT_CODE -> {
                        game = game.moveMan(Direction.LEFT)
                        arena.drawGame(game)

                    }

                    DOWN_CODE -> {
                        game = game.moveMan(Direction.DOWN)
                        arena.drawGame(game)

                    }
                }
            }
            onFinish {}
        }
    }
    onFinish {}
        }
    }
}