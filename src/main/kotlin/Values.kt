import java.awt.Color
import kotlin.math.PI
import kotlin.math.min


// stars data
var numberOfStars = 100// * 2000


val G = 6.67e-11 // гравитационная постоянная


val minMass = 3285e20
val maxMass = 59740e20
val sunMass = 2e30

val minRadius = 1
val maxRadius = 5
val sunRadius = 10




val starsColors = listOf(
    Color(0xFFFEF2),
    Color(0xD6F5FC),
    Color(0xB7EDFD),
    Color(0x55C1F4),
    Color(0x2164F5),
    Color(0xE33126),
    Color(0xF3AE3D),
    Color(0xF08A34),
    Color(0xEC602A),
    Color(0xFDF47E)

)



// visual data
val screenWidth = 780
val screenHeight = 780

val numberOfSquares = min(screenWidth, screenHeight) / sunRadius / 2

val squareSize = screenWidth / numberOfSquares

val systemRadius = 1e12 // km

val kmInPx = systemRadius / (screenWidth / 2)
val distanceBetweenAddingStars = 12 * kmInPx

val sleepTime = 2L // millis

