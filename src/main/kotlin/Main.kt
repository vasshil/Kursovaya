import objects.Galaxy
import objects.Star
import objects.sun
import view.GraphicsWindow
import view.InfoWindow
import java.util.*
import kotlin.math.*


val galaxy = Galaxy()

var systemTime = 0.0
var starsCounter = numberOfStars

var starsChartData = MutableList(starsColors.size) { 0 }

fun main() {

    galaxy.generateStars()
    println(starsChartData)

    val window = GraphicsWindow()

    val infoWindow = InfoWindow()
    infoWindow.updateSelectedStar(sun)


    while (true) {
        // doing on every iteration
        systemTime += dt

        galaxy.move()

        window.update()

        infoWindow.updateSelectedStarInfoLabel()
        infoWindow.updateStarsInfoLabel()
//        infoWindow.updateStarsInfoChart()


        Thread.sleep(sleepTime)
    }

}

