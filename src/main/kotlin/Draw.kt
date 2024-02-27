import pt.isel.canvas.Canvas
import pt.isel.canvas.WHITE

fun Canvas.drawSprite(sLine: Int, sCol: Int, pos: Position) {
    val col = sCol * SPRITE_WIDTH + 1
    val line = sLine * SPRITE_HEIGHT + 2
    val img = "soko.png|$col,$line,${SPRITE_WIDTH - 1},${SPRITE_HEIGHT - 1}"
    drawImage(img, pos.col * SPRITE_WIDTH, pos.line * SPRITE_HEIGHT, SPRITE_WIDTH, SPRITE_HEIGHT)
}

fun Canvas.drawGame(game: Game) {
    erase()
    drawGrid()
    targets(game.targets)
    drawBoxes(game)
    game.man.drawMan(this)
    walls(game.walls)
}

fun Canvas.drawGrid() {
    (SPRITE_HEIGHT..height step SPRITE_HEIGHT).forEach {
        drawLine(0, it, width, it, WHITE, 1)
    }
    (SPRITE_WIDTH..width step SPRITE_WIDTH).forEach {
        drawLine(it, 0, it, height, WHITE, 1)
    }
}

fun Canvas.drawWalls(wall: Position) {
    val x = wall.col * SPRITE_WIDTH
    val y = wall.line * SPRITE_HEIGHT
    drawImage("soko.png|45,218,35,52", x, y, SPRITE_WIDTH, SPRITE_HEIGHT)
}


fun Canvas.walls(walls: List<Position>) {
    walls.forEach {
        drawWalls(it)
    }
}


fun Canvas.drawTargets(targets: Position) {
    val x = targets.col * SPRITE_WIDTH
    val y = targets.line * SPRITE_HEIGHT
    drawImage("soko.png|0,218,35,52", x, y, SPRITE_WIDTH, SPRITE_HEIGHT)
}


fun Canvas.targets(targets: List<Position>) {
    targets.forEach {
        drawTargets(it)
    }
}


fun Canvas.drawBoxes(game: Game) = game.boxes.forEach {
    drawSprite(4, if (it in game.targets) 3 else 2, it)
}

fun Man.drawMan(canvas: Canvas) {
    if (pushing) {
        when (direction) {
            Direction.UP -> canvas.drawSprite(0, 4, pos)
            Direction.DOWN -> canvas.drawSprite(2, 4, pos)
            Direction.LEFT -> canvas.drawSprite(3, 5, pos)
            Direction.RIGHT -> canvas.drawSprite(1, 5, pos)
        }
    } else
        when (direction) {
            Direction.UP -> canvas.drawSprite(0, 1, pos)
            Direction.DOWN -> canvas.drawSprite(2, 1, pos)
            Direction.LEFT -> canvas.drawSprite(3, 1, pos)
            Direction.RIGHT -> canvas.drawSprite(1, 1, pos)
        }
}



//0 54 108 162 216 270 324


/*fun Game.move(keycode: Int): Game {
    val direction = when (keycode) {
        DOWN_CODE -> Direction.DOWN
        UP_CODE -> Direction.UP
        RIGHT_CODE -> Direction.RIGHT
        else -> Direction.LEFT
    }
    val man = this.man.copy(direction = direction)
    val newMan = man.manMove(direction)

    if (((collisionWall(newMan, this)) || ((collisionBox(newMan, this)))))  return this.copy(man = man)
    else return this.copy(man = newMan)

    }
 */