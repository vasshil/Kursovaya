import java.awt.Color
import java.util.*
import kotlin.math.*

class Star(
    val name: String,
    var x: Double,
    var y: Double,
    var angle: Double,
    private val rx: Double,
    private val ry: Double,
    var mass: Double,
    var speed: Vector) {

    var radius: Int = 0
    lateinit var color: Color

    private var da: Double

    init {
        updateStarRadius()
        updateStarColor()
        da = Random().nextDouble() * maxAngle
    }

    fun move() {
        angle += da

        x = rx * cos(angle) + sun.x
        y = ry * sin(angle) + sun.y

    }

    fun add(star: Star) {
        mass += star.mass
        speed.add(star.speed)
        da = (da + star.da) / 2
        updateStarRadius()
        updateStarColor()

    }

    fun getDistanceToStar(star: Star): Double {
        return hypot(abs(x - star.x), abs(y - star.y)) - (radius + star.radius) / 2
    }

    private fun updateStarRadius(){
        if (mass <= maxMass) {
//            println(linearInterpolation(minMass, maxMass, minRadius.toDouble(), maxRadius.toDouble(), mass))
            radius = round(linearInterpolation(minMass, maxMass, minRadius.toDouble(), maxRadius.toDouble(), mass)).toInt()
        } else if (mass <= sunMass) {
            radius = round(linearInterpolation(maxMass, sunMass, maxRadius + 1.0, sunRadius.toDouble(), mass)).toInt()
        }
//        println(radius)
    }

    private fun updateStarColor() {
        color = starsColors[radius - 1]
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







