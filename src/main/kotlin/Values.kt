import java.awt.Color
import kotlin.math.PI
import kotlin.math.min


// stars data
var numberOfStars = 1000// * 2000

val dt = 1e4

val G = 6.67e-11 // гравитационная постоянная


val minMass = 3285e20
val maxMass = 59740e20
val sunMass = 2e30

val minRadius = 1
val maxRadius = 8
val sunRadius = 10




val starsColors = listOf(
    Color(0xFFFEF2),
    Color(0xD6F5FC),
    Color(0xB7EDFD),
    Color(0x55C1F4),
    Color(0x07a0a6),
    Color(0x2164F5),
    Color(0x9004ba),
    Color(0x046e02),
    Color(0xafd106),
    Color(0xfccf05)  // sun

)



// visual data
val screenWidth = 780
val screenHeight = 780

val numberOfSquares = min(screenWidth, screenHeight) / sunRadius / 2

val squareSize = screenWidth / numberOfSquares

val systemRadius = 1e12 // km

val kmInPx = systemRadius / (screenWidth / 2)
val distanceBetweenAddingStars = 3 * kmInPx

val sleepTime = 0L // millis


// Info Window

val infoScreenWidth = 500
val infoScreenHeight = 450
val infoScreenPadding = 10

