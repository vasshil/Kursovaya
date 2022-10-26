package view

import screenHeight
import screenWidth
import java.awt.Dimension
import java.awt.event.ComponentEvent
import java.awt.event.ComponentListener
import javax.swing.JFrame

class GraphicsWindow: JFrame(), ComponentListener {

    private var canvas: Canvas = Canvas()

    init {

        canvas.preferredSize = Dimension(screenWidth, screenHeight)
        contentPane.add(canvas)

        pack()
        addComponentListener(this)

        title = "Курсовая"
        setLocationRelativeTo(null)
        defaultCloseOperation = EXIT_ON_CLOSE

        isVisible = true

    }

    fun update() {
        canvas.repaint()
    }




    override fun componentResized(e: ComponentEvent?) {
//        println("resized ${canvas.width} ${canvas.height}")
    }

    override fun componentMoved(e: ComponentEvent?) {}

    override fun componentShown(e: ComponentEvent?) {}

    override fun componentHidden(e: ComponentEvent?) {}


}