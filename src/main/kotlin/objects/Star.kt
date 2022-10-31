package objects

import G
import dt
import kmInPx
import maxMass
import maxRadius
import minMass
import minRadius
import numberOfSquares
import squareSize
import starsChartData
import starsColors
import sunMass
import sunRadius
import java.awt.Color
import kotlin.math.*

class Star(
    val name: String,
    var x: Double,
    var y: Double,
    var mass: Double,
    private val angle: Double,
    var i: Int,
    var j: Int) {

    var radius: Int = -1
    lateinit var color: Color

    lateinit var speed: Vector

    var forceX = 0.0
    var forceY = 0.0



    init {
        updateStarRadius()
        updateStarColor()
        setSpeed()

//        starsChartData[radius - 1] ++

    }

    private fun updateStarRadius() {
        if (radius != -1) {
            starsChartData[radius - 1]--
        }

        if (mass <= maxMass) {
            radius = round(linearInterpolation(minMass, maxMass, minRadius.toDouble(), maxRadius.toDouble(), mass)).toInt()
        } else if (mass <= sunMass) {
            radius = round(linearInterpolation(maxMass, sunMass, maxRadius + 1.0, sunRadius.toDouble(), mass)).toInt()
        }

        starsChartData[radius - 1]++

    }

    private fun linearInterpolation(
        minIn: Double, maxIn: Double,
        minOut: Double, maxOut: Double,
        number: Double
    ): Double {
//        println("$number")

        return minOut + ((number - minIn) / (maxIn - minIn)) * (maxOut - minOut)

    }

    private fun updateStarColor() {
        color = starsColors[(radius - 1)]
    }

    private fun setSpeed() {

        if (name != "SUN") {
            val R = getDistanceToStar(sun) + sunRadius / 2 // R = h + r
            val secondSpaceSpeed = sqrt(G * sun.mass / R)

            speed = Vector(
                secondSpaceSpeed * sin(angle),
                -secondSpaceSpeed * cos(angle)
            )
        } else {
            speed = Vector(0.0, 0.0)
        }


    }



    fun add(star: Star) {
        if (name != "SUN" && star.name != "SUN") {

            val sumMass = mass + star.mass

            x = (x * mass + star.x * star.mass) / sumMass
            y = (y * mass + star.y * star.mass) / sumMass

            mass = sumMass

            speed.x = (speed.x * mass + star.speed.x * star.mass) / sumMass
            speed.y = (speed.y * mass + star.speed.y * star.mass) / sumMass


            updateStarRadius()
            updateStarColor()

//            starsChartData[star.radius - 1]--

        }

    }

    fun checkNeighbourStarsInfluence() {

        val neighbourStars = getNeighbourStars()


        for (neighbourStar in neighbourStars) {
            val distance = getDistanceToStar(neighbourStar)

            val v = (2 * G * mass * neighbourStar.mass / distance / distance)

            forceX -= v * (x - neighbourStar.x) / distance
            neighbourStar.forceX += v * (x - neighbourStar.x) / distance

            forceY -= v * (y - neighbourStar.y) / distance
            neighbourStar.forceY += v * (x - neighbourStar.x) / distance

        }

    }

    fun getDistanceToStar(star: Star): Double {
        return hypot((x - star.x), (y - star.y)) - (radius + star.radius) / 2
    }

    fun getNeighbourStars(): MutableList<Star> {
        val neighbourDistance = squareSize * kmInPx / 2

        val neighbourStars = mutableListOf<Star>()

        val neighbourSquares = getNeighbourSquares()

        for (square in neighbourSquares) {
            for (neighbourStar in stars[square.first][square.second].toList()) {
                if (
                    neighbourStar.x in (x - neighbourDistance)..(x + neighbourDistance) &&
                    neighbourStar.y in (y - neighbourDistance)..(y + neighbourDistance)
                ) {
                    neighbourStars += neighbourStar
                }
            }

//            neighbourStars.addAll(stars[square.first][square.second].toList())
        }

        neighbourStars.add(sun)

        return neighbourStars
    }

    private fun getNeighbourSquares(): MutableList<Pair<Int, Int>> { // return 9 squares to check
        val neighbourSquares = mutableListOf(Pair(i, j))

        if (!(j == 0 || j == numberOfSquares - 1 || i == 0 || i == numberOfSquares - 1)) { // square is not on the side

            neighbourSquares += Pair(i - 1, j - 1)
            neighbourSquares += Pair(i - 1, j)
            neighbourSquares += Pair(i - 1, j + 1)
            neighbourSquares += Pair(i, j - 1)
            neighbourSquares += Pair(i, j + 1)
            neighbourSquares += Pair(i + 1, j - 1)
            neighbourSquares += Pair(i + 1, j)
            neighbourSquares += Pair(i + 1, j + 1)

        } else {


        }

        return neighbourSquares
    }

    fun move() {

//        var distance: Double
//        var dCoordinate = arrayOf(0.0, 0.0)
//
//        forceX = 0.0
//        forceY = 0.0


//        val dt = 10000

        speed.x += dt * forceX / mass
        speed.y += dt * forceY / mass

        x += dt * speed.x
        y += dt * speed.y


//        println("${speed.x * dt / kmInPx} ${speed.y * dt / kmInPx}")


    }



    fun setFromString(data: String) {

    }

    override fun toString(): String {
        return "$name $x $y $mass $speed"
    }


}







