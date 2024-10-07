package yw.makery.address.view
import scalafxml.core.macros.sfxml
import yw.makery.address.MainApp
@sfxml
class IntroController{
  def getStart(): Unit = {
    MainApp.showUser()
  }
  def getRule(): Unit = {
    MainApp.showRule()
  }
  def getLeaderBoard(): Unit = {
    MainApp.showLeaderBoard()
  }
}