import java.awt.Color
import java.util.*
import kotlin.math.*

class Star(
    val name: String,
    var x: Double,
    var y: Double,
    var mass: Double,
    val angle: Double,
    var i: Int,
    var j: Int) {

    var radius: Int = 0
    lateinit var color: Color

    lateinit var speed: Vector

    var forceX = 0.0
    var forceY = 0.0



    init {
        updateStarRadius()
        updateStarColor()
        updateSpeed()

    }

    fun move() {

//        var distance: Double
//        var dCoordinate = arrayOf(0.0, 0.0)

        forceX = 0.0
        forceY = 0.0

        val neighbourStars = getNeighbourStars()

        for (neighbourStar in neighbourStars) {
            val distance = getDistanceToStar(neighbourStar)

            val v = G * mass * neighbourStar.mass / distance

            forceX -= v * (x - neighbourStar.x) / distance
            neighbourStar.forceX += v * (x - neighbourStar.x) / distance

            forceY -= v * (x - neighbourStar.x) / distance
            neighbourStar.forceY += v * (x - neighbourStar.x) / distance

            val dt = 1

            speed.x += dt * forceX / mass
            speed.y += dt * forceY / mass

            x += dt * speed.x
            y += dt * speed.y


        }





//        for (i in 0 until neighbourStars.size) {
//            for (j in i + 1 until neighbourStars.size) {
////                distance = 0.0
//            }
//        }




//        angle += da
//        x = rx * cos(angle) + sun.x
//        y = ry * sin(angle) + sun.y



    }

    private fun getNeighbourStars(): MutableList<Star> {
        val neighbourStars = mutableListOf<Star>()

        val neighbourSquares = getNeighbourSquares()

        for (square in neighbourSquares) {
            neighbourStars.addAll(stars[square.first][square.second].toList())
        }

        return neighbourStars
    }

    fun getNeighbourSquares(): MutableList<Pair<Int, Int>> { // return 9 squares to check
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

    fun add(star: Star) {

        val sumMass = mass + star.mass

        x = (x * mass + star.x * star.mass) / sumMass
        y = (y * mass + star.y * star.mass) / sumMass

        mass = sumMass

        speed.x = (speed.x * mass + star.speed.x * star.mass) / sumMass
        speed.y = (speed.y * mass + star.speed.y * star.mass) / sumMass

//        speed.add(star.speed)
//        da = (da + star.da) / 2
        updateStarRadius()
        updateStarColor()

    }

    fun getDistanceToStar(star: Star): Double {
        return hypot(abs(x - star.x), abs(y - star.y)) - (radius + star.radius) / 2
    }

    private fun updateStarRadius(){
        if (mass <= maxMass) {
            radius = round(linearInterpolation(minMass, maxMass, minRadius.toDouble(), maxRadius.toDouble(), mass)).toInt()
        } else if (mass <= sunMass) {
            radius = round(linearInterpolation(maxMass, sunMass, maxRadius + 1.0, sunRadius.toDouble(), mass)).toInt()
        }
    }

    private fun updateStarColor() {
        color = starsColors[radius - 1]
    }

    private fun updateSpeed() {

        if (name != "SUN") {
            val R = getDistanceToStar(sun) + sunRadius / 2 // R = h + r
            val secondSpaceSpeed = sqrt(2 * G * sun.mass / R)

            speed = Vector(
                secondSpaceSpeed * sin(angle),
                -secondSpaceSpeed * cos(angle)
            )
        }


    }

    private fun linearInterpolation(
        minIn: Double, maxIn: Double,
        minOut: Double, maxOut: Double,
        number: Double
    ): Double {
//        println("$number")

        return minOut + ((number - minIn) / (maxIn - minIn)) * (maxOut - minOut)

    }



    override fun toString(): String {
        return name
    }


}







