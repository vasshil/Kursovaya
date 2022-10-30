package view

import infoScreenPadding
import maxRadius
import minRadius
import starsChartData
import starsColors
import sunRadius
import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D
import javax.swing.JPanel

class ChartView: JPanel() {

    val chartHeight = (infoScreenPadding + 2 * minRadius + infoScreenPadding + 2 * sunRadius) * starsChartData.size / 2

    private lateinit var graphics: Graphics2D

    override fun paintComponent(g: Graphics?) {
        super.paintComponent(g)

        graphics = g as Graphics2D
//
//        graphics.color = Color.BLACK
//        graphics.fillRect(0, 0, width, height)

        // draw stars
        val x = starsChartData.size - 1 + infoScreenPadding
        var y = 0
        val d = -2

        for (r in sunRadius downTo minRadius) {

            y += infoScreenPadding + 2 * r

            println("$x, $y, $r")
            graphics.color = starsColors[r - 1]
            graphics.fillOval(
                x - r,
                y,
                2 * r,
                2 * r)

            graphics.color = Color.BLACK
            graphics.drawOval(
                x - r,
                y,
                2 * r,
                2 * r)

            y += d

        }

    }

}