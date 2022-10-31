package view.info

import objects.Star
import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D
import javax.swing.JPanel
import kotlin.math.*

class SelectedStarView: JPanel() {

    private lateinit var starToDraw: Star

    private lateinit var graphics: Graphics2D

    override fun paintComponent(g: Graphics?) {
        super.paintComponent(g)

        graphics = g as Graphics2D

        // bg
        graphics.color = Color.BLACK
        graphics.fillRoundRect(0, 0, width, height, 10, 10)

        // direction
//        var angle = atan((starToDraw.speed.y / starToDraw.speed.x))
//        if (angle > PI / 4) angle *= -1
//        val r = width / 2
//        graphics.color = Color.RED
//        graphics.drawLine(
//            width / 2,
//            height / 2,
//            (width / 2 + r * cos(angle)).toInt(),
//            (height / 2 + r * sin(angle)).toInt()
//        )

//        println(angle)

        // star
        graphics.color = starToDraw.color
        graphics.fillOval(
            width / 2 - starToDraw.radius,
            height / 2 - starToDraw.radius,
            2 * starToDraw.radius,
            2 * starToDraw.radius)

    }

    fun setStar(star: Star) {
        starToDraw = star
        repaint()
    }

}