import view.GraphicsWindow
import java.util.*
import kotlin.math.*

var stars = MutableList(numberOfSquares) { MutableList(numberOfSquares) { mutableSetOf<Star>() } }
lateinit var sun: Star


val random = Random()

fun main() {

    generateStars()

    val window = GraphicsWindow()

    while (true) {

        // list of stars, which needed to move
        val starsToMove = mutableMapOf<Pair<Pair<Int, Int>, Pair<Int, Int>>, Star>()

        // checking all stars
        for (i in 0 until numberOfSquares) {
            for (j in 0 until numberOfSquares) {
//                if (stars[i][j].size > 0) println(stars[i][j])
                add@ for (star in stars[i][j].toList()) {
//                    val star = stars[i][j].toList()[starI]

                    // checking if 2 stars can be added
                    val neighbourSquares = star.getNeighbourSquares()
                    for (square in neighbourSquares) {
                        for (neighbourStar in stars[square.first][square.second].toList()) {
                            if (neighbourStar != star) {
                                if (star.getDistanceToStar(neighbourStar) <= distanceBetweenAddingStars) {
                                    star.add(neighbourStar) // !!!!!

                                    stars[square.first][square.second].remove(neighbourStar)

                                    println("added ${neighbourStar.name} to ${star.name}; removed ${neighbourStar.name}")
                                    break@add
                                }
                            }
                        }
                    }

                    // move star
                    star.move()

                    // checking if star changes square
                    val newI = (star.y / squareSize).toInt()
                    val newJ = (star.x / squareSize).toInt()

                    if (newI != i || newJ != j) {
                        starsToMove += Pair(Pair(i, j), Pair(newI, newJ)) to star

                        star.i = newI
                        star.j = newJ

                    }

                }
            }
        }

        // changing squares of stars
        for ((positions, star) in starsToMove) {
            stars[positions.second.first][positions.second.second].add(star)
            stars[positions.first.first][positions.first.second].remove(star)


        }

        window.update()
        Thread.sleep(5)
    }

}

private fun generateStars() {

    sun = Star(
        "SUN",
        screenWidth / 2.0,
        screenHeight / 2.0,
        sunMass,
        0.0,
        -1,
        -1
    )


    for (n in 0 until numberOfStars) {
        val rx = (random.nextInt(screenWidth / 2 - 3 * squareSize) + 2 * squareSize).toDouble()
        val ry = (random.nextInt(screenHeight / 2 - 3 * squareSize) + 2 * squareSize).toDouble()
        val angle = random.nextDouble() * (2 * PI)

        val x = rx * cos(angle) + sun.x
        val y = ry * sin(angle) + sun.y

        val i = (y / squareSize).toInt()
        val j = (x / squareSize).toInt()

        stars[i][j].add(
            Star(
                "Star-${n + 1}",
                x,
                y,
                getRandomMass(),
                angle,
                i,
                j
            )
        )

    }

}

private fun getRandomMass(): Double {
    return minMass + random.nextDouble() * (maxMass - minMass)
}

