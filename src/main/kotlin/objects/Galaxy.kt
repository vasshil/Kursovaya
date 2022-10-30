package objects

import distanceBetweenAddingStars
import kmInPx
import maxMass
import minMass
import numberOfSquares
import numberOfStars
import screenHeight
import screenWidth
import squareSize
import starsCounter
import sunMass
import systemRadius
import java.util.*
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sin

var stars = MutableList(numberOfSquares) { MutableList(numberOfSquares) { mutableSetOf<Star>() } }
lateinit var sun: Star

class Galaxy {


    private val random = Random()

    var initializated = false


    fun move() {

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

        // checking all stars for adding
        for (i in 0 until numberOfSquares) {
            for (j in 0 until numberOfSquares) {
                add@ for (star in stars[i][j].toList()) {

//                    star.checkNeighbourStarsInfluence()

                    for (neighbourStar in star.getNeighbourStars()) {

                        // checking if 2 stars can be added
                        if (neighbourStar != star) {
                            if (star.getDistanceToStar(neighbourStar) <= distanceBetweenAddingStars) {
                                star.add(neighbourStar)

                                if (stars[neighbourStar.i][neighbourStar.j].remove(neighbourStar)) {
                                    starsCounter--
                                }


                                break@add
                            }
                        }
                    }

                }
            }
        }

//        sun.checkNeighbourStarsInfluence()
//        sun.move()

        // checking all stars for gravitation influence
        for (i in 0 until numberOfSquares) {
            for (j in 0 until numberOfSquares) {
                for (star in stars[i][j].toList()) {

                    star.checkNeighbourStarsInfluence()

                }
            }
        }


        // list of stars, which needed to move
        val starsToMove = mutableListOf<Star>()

        // moving stars
        for (i in 0 until numberOfSquares) {
            for (j in 0 until numberOfSquares) {
                for (star in stars[i][j].toList()) {

                    // move star
                    star.move()

                    if (getWindowCoordinate(star.x, screenWidth) !in 0 until screenWidth ||
                        getWindowCoordinate(star.y, screenHeight) !in 0 until screenHeight) {
                        stars[i][j].remove(star)
                        starsCounter--

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

    }

    fun generateStars() {
        initializated = true

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

            val x = r * cos(angle) * 0.9// + r * (cos(angle) / abs(cos(angle))) * 0.1
            val y = r * sin(angle) * 0.9// + r * (sin(angle) / abs(sin(angle))) * 0.1

            val i = (getWindowCoordinate(y, screenHeight) / squareSize).toInt()
            val j = (getWindowCoordinate(x, screenWidth) / squareSize).toInt()

            stars[i][j].add(
                Star(
                    "objects.Star-${n + 1}",
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


    private fun loadStarsState() {
        initializated = true

    }

    private fun saveStarsState() {

    }


}

public fun getWindowCoordinate(value: Double, maxCoordinate: Int): Int {
    return ((value / systemRadius + 1) * (maxCoordinate / 2)).toInt()
}
