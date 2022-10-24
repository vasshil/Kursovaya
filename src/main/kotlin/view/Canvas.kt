package view

import Star
import numberOfSquares
import stars
import java.awt.Graphics
import java.awt.Graphics2D
import javax.swing.JPanel

class Canvas(): JPanel() {

    private lateinit var graphics: Graphics2D

    override fun paintComponent(g: Graphics?) {
        super.paintComponent(g)

        graphics = g as Graphics2D
//        graphics.stroke = BasicStroke(strokeWidth)


        for (i in 0 until numberOfSquares) {

            for (j in 0 until numberOfSquares) {

                for (star in stars[i][j]) {
                    drawStar(star)
                }

            }

        }




    }


    private fun drawStar(star: Star) {
        graphics.drawOval(
            star.x - star.radius,
            star.y - star.radius,
            star.radius * 2,
            star.radius * 2
        )
    }


}








