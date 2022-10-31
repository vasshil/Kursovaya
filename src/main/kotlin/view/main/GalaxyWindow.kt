package view.main

import infoWindow
import numberOfSquares
import objects.Star
import objects.getWindowCoordinate
import objects.stars
import objects.sun
import screenHeight
import screenWidth
import squareSize
import view.main.GalaxyCanvas
import java.awt.Dimension
import java.awt.event.ComponentEvent
import java.awt.event.ComponentListener
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.swing.JFrame
import kotlin.math.pow
import kotlin.math.sqrt

class GalaxyWindow: JFrame(), ComponentListener {

    private var galaxyCanvas: GalaxyCanvas = GalaxyCanvas()

    init {

        galaxyCanvas.preferredSize = Dimension(screenWidth, screenHeight)
        contentPane.add(galaxyCanvas)

        galaxyCanvas.addMouseListener(object : MouseAdapter() {

            override fun mouseClicked(e: MouseEvent?) {
                super.mouseClicked(e)
                val x0 = e!!.x
                val y0 = e.y

//                val i = y0 / squareSize
//                val j = x0 / squareSize

                // check sun
                if (isClickInStar(x0, y0, sun)) {
                    println("Sun")
                    infoWindow.updateSelectedStar(sun)

                    return
                }

                // checking another stars
                for (i in 0 until numberOfSquares) {
                    for (j in 0 until numberOfSquares) {
                        for (star in stars[i][j].toList()) {
                            if (isClickInStar(x0, y0, star)) {
                                println("${star.name} ${star.color}")
                                infoWindow.updateSelectedStar(star)

                                return
                            }


                        }
                    }
                }




            }

        })

        pack()
        addComponentListener(this)

        title = "Курсовая"
//        setLocationRelativeTo(null)
        setLocation(50, 0)
        defaultCloseOperation = EXIT_ON_CLOSE

        isVisible = true

    }

    fun update() {
        galaxyCanvas.repaint()
    }


    private fun isClickInStar(x0: Int, y0: Int, star: Star): Boolean {
        val x = getWindowCoordinate(star.x, screenWidth)
        val y = getWindowCoordinate(star.y, screenHeight)

        if ((x - x0) * (x - x0) + (y - y0) * (y - y0) <= (star.radius + 3) * (star.radius + 3)) {
            return true
        }

        return false
    }



    override fun componentResized(e: ComponentEvent?) {
//        println("resized ${canvas.width} ${canvas.height}")
    }

    override fun componentMoved(e: ComponentEvent?) {}

    override fun componentShown(e: ComponentEvent?) {}

    override fun componentHidden(e: ComponentEvent?) {}


}