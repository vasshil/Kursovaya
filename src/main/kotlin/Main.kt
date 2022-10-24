import view.GraphicsWindow
import java.util.*

val stars = MutableList(numberOfSquares) { MutableList(numberOfSquares) { mutableListOf<Star>() } }
lateinit var sun: Star

fun main() {

    generateStars()

    val window = GraphicsWindow()


}

private fun generateStars() {
    sun = Star(screenWidth / 2, screenHeight / 2, 20)

    val random = Random()

    for (i in 0 until numberOfStars) {
        val x = random.nextInt(screenWidth)
        val y = random.nextInt(screenHeight)

        stars[y / squareSize][x / squareSize].add(Star(x, y, 2))


    }



}

