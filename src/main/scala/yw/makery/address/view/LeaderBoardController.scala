package yw.makery.address.view

import scalafxml.core.macros.sfxml
import javafx.scene.control.{TableColumn, TableView}
import yw.makery.address.model.LeaderBoard
import yw.makery.address.MainApp
import javafx.beans.property.ReadOnlyObjectWrapper
import scalafx.Includes._

@sfxml
class LeaderBoardController(
                             private val leaderboardTable: TableView[LeaderBoard],
                             private val usernameColumn: TableColumn[LeaderBoard, String],
                             private val attemptColumn: TableColumn[LeaderBoard, Number]
                           ) {
  // Fetch top 10 leaderboard data and populate the TableView
  MainApp.leaderboardData.clear()
  MainApp.leaderboardData ++= LeaderBoard.fetchTop10()
  leaderboardTable.items = MainApp.leaderboardData

  // Set cell value factories
  usernameColumn.cellValueFactory = (x) => x.value.username
  attemptColumn.cellValueFactory = (x) => new ReadOnlyObjectWrapper[Number](x.value.attempt.value)

  def getBack(): Unit = {
    MainApp.showIntro()
  }
}