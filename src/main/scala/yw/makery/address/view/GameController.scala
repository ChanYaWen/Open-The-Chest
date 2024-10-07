package yw.makery.address.view

import scalafxml.core.macros.sfxml
import javafx.scene.control.{TableColumn, TableView, TextField}
import javafx.collections.{FXCollections, ObservableList}
import scalafx.scene.control.Alert
import scalafx.scene.control.Alert.AlertType
import yw.makery.address.MainApp
import yw.makery.address.model.LeaderBoard
import yw.makery.address.model.Feedback


import scala.util.Random

@sfxml
class GameController(
                      private val inputField: TextField,
                      private val feedbackTable: TableView[Feedback],
                      private val inputColumn: TableColumn[Feedback, String],
                      private val feedbackColumn: TableColumn[Feedback, String]
                    ) {

  private val secretCode = generateSecretCode()

  // ObservableList to hold the data for the TableView
  private val data: ObservableList[Feedback] = FXCollections.observableArrayList()

  inputColumn.setCellValueFactory(cellData => cellData.getValue.guessProperty)
  feedbackColumn.setCellValueFactory(cellData => cellData.getValue.feedbackProperty)

  feedbackTable.setItems(data) // Bind the ObservableList to the TableView

  def getEnter(): Unit = {
    val guess = inputField.getText.trim
    if (isValidGuess(guess)) {
      MainApp.attempt += 1
      val feedback = getFeedback(secretCode, guess)
      data.add(new Feedback(guess, s"${feedback._1}A${feedback._2}B"))

      if (feedback._1 == 4) {
        MainApp.showWinning()
        var leaderBoardEntry = new LeaderBoard(usernameS = MainApp.username, attemptS = MainApp.attempt)
        leaderBoardEntry.save()
      }
    } else {
      new Alert(AlertType.Warning) {
        initOwner(MainApp.stage)
        title = "Error!"
        headerText = "Invalid Input!"
        contentText = "The input should be 4 unique digits"
      }.showAndWait()
    }
    inputField.clear()
  }

  def generateSecretCode(): String = {
    val digits = Random.shuffle((0 to 9).toList).take(4)
    digits.mkString("")
  }

  def isValidGuess(guess: String): Boolean = {
    guess.length == 4 && guess.forall(_.isDigit) && guess.distinct.length == 4
  }

  def getFeedback(secretCode: String, guess: String): (Int, Int) = {
    val A = secretCode.zip(guess).count { case (s, g) => s == g }
    val B = guess.count(g => secretCode.contains(g)) - A
    (A, B)
  }
}