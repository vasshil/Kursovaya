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
        // doing on every iteration

        // null force
        for (i in 0 until numberOfSquares) {
            for (j in 0 until numberOfSquares) {
                for (star in stars[i][j].toList()) {
                    star.forceX = 0.0
                    star.forceY = 0.0

                }
            }
        }

        sun.forceX = 0.0
        sun.forceY = 0.0

//        sun.checkNeighbourStarsInfluence()
//        sun.move()

        // list of stars, which needed to move
        val starsToMove = mutableListOf<Star>()

        // checking all stars for adding and gravitation influence
        for (i in 0 until numberOfSquares) {
            for (j in 0 until numberOfSquares) {
                add@ for (star in stars[i][j].toList()) {

                    star.checkNeighbourStarsInfluence()

                    for (neighbourStar in star.getNeighbourStars()) {

                        // checking if 2 stars can be added
                        if (neighbourStar != star) {
//                            println("has neighbours ${star.getDistanceToStar(neighbourStar)} ${distanceBetweenAddingStars}")
                            if (star.getDistanceToStar(neighbourStar) <= distanceBetweenAddingStars) {
                                println("add")
                                star.add(neighbourStar) // !!!!!

                                stars[neighbourStar.i][neighbourStar.j].remove(neighbourStar)

                                println("added ${neighbourStar.name} to ${star.name}; removed ${neighbourStar.name}")
                                break@add
                            }
                        }
                    }

                }
            }
        }

        // moving stars
        for (i in 0 until numberOfSquares) {
            for (j in 0 until numberOfSquares) {
                for (star in stars[i][j].toList()) {

                    // move star
                    star.move()

                    if (getWindowCoordinate(star.x, screenWidth) !in 0 until screenWidth ||
                        getWindowCoordinate(star.y, screenHeight) !in 0 until screenHeight) {
                        stars[i][j].remove(star)
                        continue
                    }

                    // checking if star changes square
                    val newI = getWindowCoordinate(star.y, screenHeight) / squareSize
                    val newJ = getWindowCoordinate(star.x, screenWidth) / squareSize
                    if (newI != i || newJ != j) {
                        starsToMove += star

                    }

                }
            }
        }

        // changing squares of stars
        for (star in starsToMove) {
            if (star.name != "SUN") {
                val newI = getWindowCoordinate(star.y, screenHeight) / squareSize
                val newJ = getWindowCoordinate(star.x, screenWidth) / squareSize

                stars[newI][newJ].add(star)
                stars[star.i][star.j].remove(star)

                star.i = newI
                star.j = newJ

            }
        }

        window.update()
        Thread.sleep(sleepTime)
    }

}

private fun generateStars() {

    sun = Star(
        "SUN",
        0.0,
        0.0,
        sunMass,
        0.0,
        screenWidth / 2 / squareSize,
        screenHeight / 2 / squareSize
    )


//    stars[sun.i][sun.j].add(sun)


    for (n in 0 until numberOfStars) {
        val r = random.nextDouble() * systemRadius
        val angle = random.nextDouble() * (2 * PI)

        val x = r * cos(angle) * 0.9 //rx * cos(angle) + sun.x
        val y = r * sin(angle) * 0.9 //ry * sin(angle) + sun.y

        val i = (getWindowCoordinate(y, screenHeight) / squareSize).toInt()
        val j = (getWindowCoordinate(x, screenWidth) / squareSize).toInt()

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

//        println("$x $y $i $j")

    }

}

private fun getRandomMass(): Double {
    return minMass + random.nextDouble() * (maxMass - minMass)
}

fun getWindowCoordinate(value: Double, maxCoordinate: Int): Int {
    return ((value / systemRadius + 1) * (maxCoordinate / 2)).toInt()
}

