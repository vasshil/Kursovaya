package view

import java.awt.FlowLayout
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextField

class InfoWindow(circlesCount: Int, rectCount: Int): JFrame("Информация") {

    private val circlesCountLabel: JLabel
    private val rectCountLabel: JLabel
    private val circlesReflectionsLabel: JLabel
    private val rectReflectionsLabel: JLabel

    init {

        defaultCloseOperation = EXIT_ON_CLOSE;

        circlesCountLabel = JLabel("Количество окружностей: $circlesCount")
        rectCountLabel = JLabel("Количество прямоугольников: $rectCount")
        circlesReflectionsLabel = JLabel()
        rectReflectionsLabel = JLabel()

        setCirclesReflectionCount(0)
        setRectReflectionCount(0)

        val panel = JPanel(FlowLayout(FlowLayout.LEFT))

        panel.add(circlesCountLabel)
        panel.add(rectCountLabel)
        panel.add(circlesReflectionsLabel)
        panel.add(rectReflectionsLabel)

//        add(panel)
        contentPane = panel

        pack()

        setSize(400, 300)

        setLocationRelativeTo(null)
        isVisible = true

    }

    fun setCirclesReflectionCount(count: Int) {
        circlesReflectionsLabel.text = "Количество отражений окружностей: $count"
    }

    fun setRectReflectionCount(count: Int) {
        rectReflectionsLabel.text = "Количество отражений прямоугольников: $count"
    }

}