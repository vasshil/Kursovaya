package view

import objects.Star
import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D
import javax.swing.JPanel

class SelectedStarView: JPanel() {

    private lateinit var starToDraw: Star

    private lateinit var graphics: Graphics2D

    override fun paintComponent(g: Graphics?) {
        super.paintComponent(g)

        graphics = g as Graphics2D

        graphics.color = Color.BLACK
        graphics.fillRect(0, 0, width, height)

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