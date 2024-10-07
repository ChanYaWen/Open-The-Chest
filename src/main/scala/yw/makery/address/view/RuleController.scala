package yw.makery.address.view
import scalafxml.core.macros.sfxml
import yw.makery.address.MainApp
@sfxml
class RuleController {
  def getBack(): Unit = {
    MainApp.showIntro()
  }
}
