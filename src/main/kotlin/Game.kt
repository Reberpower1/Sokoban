data class Dimension(val WIDTH: Int, val HEIGHT: Int) // Dimensões totais da grelha
data class Game(
    val dim: Dimension,
    val man: Man, val walls: List<Position>,
    val boxes: List<Position>,
    val targets: List<Position>,
)

enum class Direction(val lineDif: Int, val colDif: Int) {
    LEFT(0, -1),
    RIGHT(0, +1),
    UP(-1, 0),
    DOWN(+1, 0)
}

//fazer função createGame() para aquela situação
fun createGame(): Game {
    val maze = loadMap(level1)
    val man = maze.cells.first { it.type == Type.MAN }.pos
    return Game(
        dim = Dimension(maze.width, maze.height),
        man = Man(man, Direction.DOWN),
        walls = getWalls(maze),
        targets = getTargets(maze),
        boxes = getBoxes(maze)
    )
}


fun getBoxes(maze: Maze): List<Position> {
    return maze.positionsOfType(Type.BOX)
}

fun getWalls(maze: Maze): List<Position> {
    return maze.positionsOfType(Type.WALL)
}

fun getTargets(maze: Maze): List<Position> {
    return maze.positionsOfType(Type.TARGET)
}

fun Game.collisionBoxes(): Game =
    if (collisionBox(Man(man.pos.position(this.move.man), this)) copy(man = Man(man.pos, man.direction, true))
    else copy(man = Man(man.pos, man.direction, false))

fun nextposition() {


}



fun collisionTargets(boxes: List<Position>, targets: List<Position>): Boolean = boxes.all { it in targets }

fun collisionWalls(pos: Position, walls: List<Position>): Boolean {
    return pos !in walls
}


/*
fun Game.moveMan(direction: Direction): Game {
    val newMan = this.move(direction)
    return if (collisionWalls(newMan.pos, walls) && !collisionBoxes(newMan.pos, boxes))
        Game(this.dim, Man(newMan.pos, direction, false), this.walls, this.boxes, this.targets)
    else if (collisionWalls(newMan.pos, walls) && collisionBoxes(newMan.pos, boxes)) {
        val updatedBoxes = moveBoxes(newMan.pos, direction)
        if (newMan.pos !in updatedBoxes)
            Game(this.dim, Man(newMan.pos, direction, true), this.walls, updatedBoxes, this.targets)
        else Game(this.dim, Man(man.pos, direction, true), this.walls, this.boxes, this.targets)
    } else Game(this.dim, Man(man.pos, direction, false), this.walls, this.boxes, this.targets)
}
//fun Game.moveMan(dir: Direction): Game =
//  Game(this.dim, this.man.move(dir), this.walls, this.boxes, this.targets)
fun Game.moveBoxes(pos: Position, direction: Direction): List<Position> {
    val newBox = pos.move(direction)
    return if (collisionWall(newBox, walls) && !collisionBox(newBox, boxes))
        boxes - pos + newBox
    else boxes
}
 */