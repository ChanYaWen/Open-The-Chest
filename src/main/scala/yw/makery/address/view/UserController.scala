package yw.makery.address.view

import scalafxml.core.macros.sfxml
import javafx.scene.control.TextField
import scalafx.scene.control.Alert
import scalafx.scene.control.Alert.AlertType
import yw.makery.address.MainApp

@sfxml
class UserController(private val usernameField: TextField) {
  def getConfirm(): Unit = {
    if (usernameField.getText().trim != "") {
      MainApp.username = usernameField.getText().trim
      MainApp.showGame()
    } else {
      new Alert(AlertType.Warning) {
        initOwner(MainApp.stage)
        title = "Error!"
        headerText = "Empty Input!"
        contentText = "Please write your name!"
      }.showAndWait()
    }
  }
}



