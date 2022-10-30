package view

import dt
import infoScreenHeight
import infoScreenPadding
import infoScreenWidth
import objects.Star
import objects.sun
import starsCounter
import sunRadius
import systemTime
import java.awt.Dimension
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.SwingConstants

class InfoWindow: JFrame("Информация") {

    private var selectedStar = sun

    private val selectedStarView: SelectedStarView
    private val selectedStarInfoLabel: JLabel
    private val countOfStarsLabel: JLabel
    private val countOfStarsChart: JPanel

    init {

        selectedStarView = SelectedStarView()
        val selectedStarViewSize = (sunRadius + infoScreenPadding) * 4
        selectedStarView.setBounds(infoScreenPadding, infoScreenPadding, selectedStarViewSize, selectedStarViewSize)

        selectedStarInfoLabel = JLabel()
        val selectedStarInfoLabelWidth = infoScreenWidth - 2 * infoScreenPadding - selectedStarViewSize
        val selectedStarInfoLabelHeight = selectedStarViewSize
        selectedStarInfoLabel.verticalAlignment = SwingConstants.TOP
        selectedStarInfoLabel.setBounds(
            infoScreenPadding * 2 + selectedStarViewSize,
            infoScreenPadding,
            selectedStarInfoLabelWidth,
            selectedStarInfoLabelHeight)



        countOfStarsLabel = JLabel()
        countOfStarsLabel.verticalAlignment = SwingConstants.TOP
        countOfStarsLabel.setBounds(
            infoScreenPadding,
            infoScreenPadding * 2 + selectedStarInfoLabelHeight,
            infoScreenWidth,
            40)

        countOfStarsChart = JPanel()





        updateSelectedStarView()
        updateSelectedStarInfoLabel()
        updateStarsInfoLabel()


        val contentPanel = JPanel()
        contentPanel.layout = null

        contentPanel.add(selectedStarView)
        contentPanel.add(selectedStarInfoLabel)
        contentPanel.add(countOfStarsLabel)
//        contentPanel.add(countOfStarsChart)



        contentPane = contentPanel

        defaultCloseOperation = EXIT_ON_CLOSE;

        setSize(infoScreenWidth, infoScreenHeight)

        setLocationRelativeTo(null)
        isVisible = true

    }


    fun updateSelectedStar(star: Star) {
        selectedStar = star
    }

    private fun updateSelectedStarView() {
        selectedStarView.setStar(selectedStar)
    }

    fun updateSelectedStarInfoLabel() {
        selectedStarInfoLabel.text =
                "<html>" +
                    "<b>Выбранная звезда</b><br/>" +
                    "<b>-Имя:</b> ${selectedStar.name}<br/>" +
                    "<b>-Масса:</b> ${selectedStar.mass} кг<br/>" +
                    "<b>-Координаты:</b> ${selectedStar.x} ${selectedStar.y}<br/>" +
                    "<b>-Скорость:</b> ${selectedStar.speed}" +
                "</html>"

    }

    fun updateStarsInfoLabel() {
        countOfStarsLabel.text =
            "<html>" +
                    "<b>Время:</b> $systemTime часов<br/>" +
                    "<b>Количество звезд:</b> $starsCounter <br/> " +
            "</html>"
    }

}