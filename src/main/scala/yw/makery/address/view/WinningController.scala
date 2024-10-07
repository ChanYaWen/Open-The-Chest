package yw.makery.address.view

import scalafx.scene.control.Label
import scalafxml.core.macros.sfxml
import yw.makery.address.MainApp

@sfxml
class WinningController(private val attemptsLabel: Label) {
  attemptsLabel.text = MainApp.attempt.toString

  def getLeaderBoard(): Unit = {
    MainApp.showLeaderBoard()
  }
}