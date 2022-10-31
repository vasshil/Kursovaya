import objects.Galaxy
import objects.sun
import view.main.GalaxyWindow
import view.info.InfoWindow


val galaxy = Galaxy()

var systemTime = 0.0
var starsCounter = numberOfStars

var starsChartData = MutableList(starsColors.size) { 0 }

lateinit var infoWindow: InfoWindow

fun main() {

    galaxy.generateStars()
    println(starsChartData)

    val window = GalaxyWindow()
    infoWindow = InfoWindow()

    infoWindow.updateSelectedStar(sun)


    while (true) {
        // doing on every iteration
        systemTime += dt

        galaxy.move()

        window.update()

        infoWindow.updateSelectedStarView()
        infoWindow.updateSelectedStarInfoLabel()
        infoWindow.updateStarsInfoLabel()
        infoWindow.updateStarsInfoChart()


        Thread.sleep(sleepTime)
    }

}

