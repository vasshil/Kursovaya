package view

import numberOfSquares
import objects.Star
import objects.getWindowCoordinate
import objects.stars
import objects.sun
import screenHeight
import screenWidth
import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D
import javax.swing.JPanel

class Canvas: JPanel() {

    private lateinit var graphics: Graphics2D

    override fun paintComponent(g: Graphics?) {
        super.paintComponent(g)

        graphics = g as Graphics2D

        graphics.color = Color.BLACK
        graphics.fillRect(0, 0, width, height)

        for (i in 0 until numberOfSquares) {

            for (j in 0 until numberOfSquares) {

                for (star in stars[i][j].toList()) {
                    drawStar(star)
                }

            }

        }

        drawStar(sun)

    }


    private fun drawStar(star: Star) {
        graphics.color = star.color
        graphics.fillOval(
            getWindowCoordinate(star.x, screenWidth) - star.radius,
            getWindowCoordinate(star.y, screenHeight) - star.radius,
            star.radius * 2,
            star.radius * 2
        )

//        graphics.color = Color.WHITE
//        graphics.drawOval(
//            star.x - star.radius,
//            star.y - star.radius,
//            star.radius * 2,
//            star.radius * 2
//        )

    }


}








