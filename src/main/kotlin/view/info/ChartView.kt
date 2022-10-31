package view.info

import infoScreenPadding
import infoScreenWidth
import minRadius
import starsChartData
import starsColors
import starsCounter
import sunRadius
import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D
import javax.swing.JPanel

class ChartView: JPanel() {

//    val chartHeight = (infoScreenPadding + 2 * minRadius + infoScreenPadding + 2 * sunRadius) * starsChartData.size / 2
    val chartHeight = (2 * sunRadius + infoScreenPadding / 2) * starsChartData.size + infoScreenPadding

    private lateinit var graphics: Graphics2D

    override fun paintComponent(g: Graphics?) {
        super.paintComponent(g)

        graphics = g as Graphics2D

        // draw bg
        graphics.color = Color.BLACK
        graphics.fillRoundRect(0, 0, width, height, 10, 10)

        // draw stars
        val x = 3 * infoScreenPadding + 2 * sunRadius
        var y = infoScreenPadding + sunRadius


        for (r in sunRadius downTo minRadius) {

            // draw counter
            graphics.color = Color.WHITE
            graphics.drawString(starsChartData[r - 1].toString(), infoScreenPadding, y + infoScreenPadding / 2)

            // draw star
            graphics.color = starsColors[r - 1]
            graphics.fillOval(
                x - r,
                y - r,
                2 * r,
                2 * r)

            // draw bar
            val barX = x + sunRadius + infoScreenPadding * 2
            val barY = y - sunRadius / 4
            val barK = starsChartData[r - 1] / (starsCounter.toFloat() + 1) // counter + sun
            val barMW = infoScreenWidth - 10 * infoScreenPadding - 2 * sunRadius
            val barW = barK * barMW
            val barH = sunRadius / 2

            graphics.color = Color.WHITE
            graphics.fillRoundRect(barX, barY, barW.toInt(), barH, 5, 5)

            // draw bar border
            graphics.drawRoundRect(barX, barY, barMW, barH, 5, 5)



            y += sunRadius * 2 + infoScreenPadding / 2

        }


    }

}