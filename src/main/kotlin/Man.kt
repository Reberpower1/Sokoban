import pt.isel.canvas.DOWN_CODE
import pt.isel.canvas.KeyEvent
import pt.isel.canvas.RIGHT_CODE
import pt.isel.canvas.UP_CODE
import javax.swing.Box

data class Man(
    val pos: Position,
    val direction: Direction,
    val pushing: Boolean = false,
)

fun Game.move(keycode: KeyEvent): Game {
    val man = this.man.copy(direction = keycode.ChangeDirection())
    val newMan = man.manMove(keycode.ChangeDirection())
    return if(collisionWall(newMan, this) || (collisionBox(newMan, this))) this.copy(man=man)
    else this.copy(man=newMan)
}

fun KeyEvent.ChangeDirection() = when (code) {
    DOWN_CODE -> Direction.DOWN
    UP_CODE -> Direction.UP
    RIGHT_CODE -> Direction.RIGHT
    else -> Direction.LEFT
}

    fun collisionWall(man: Man, game: Game): Boolean = game.walls.filter { it == man.pos }.size != 0

    fun collisionBox(man: Man, game: Game): Boolean = game.boxes.filter { it == man.pos }.size != 0

//fun collisionTarget

    fun Man.manMove(direction: Direction): Man {
        return Man(this.pos.position(direction), direction)
    }


fun Position.position(direction: Direction) = Position(col + direction.colDif, line + direction.lineDif)

fun Game.manPushing(pos: Position, dir: Direction) =
    if (collisionBox(man, this) && !collisionWall(man, this )) copy(man = Man(pos, dir, true))
    else copy(man = Man(pos, dir, false))

fun Game.manMove(direction: Direction) = when (collisionBox(man, this)) {
    false -> if(!collisionBox(man, this)) manPushing(man.pos.position(direction), direction)
    else manPushing(man.pos, direction)
    true -> manPushing(man.pos, direction)
}