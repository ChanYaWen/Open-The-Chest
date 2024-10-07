package yw.makery.address.util

import scalikejdbc._
import yw.makery.address.model.LeaderBoard

trait Database {
  val derbyDriverClassname = "org.apache.derby.jdbc.EmbeddedDriver"
  val dbURL = "jdbc:derby:myDB;create=true;"

  // Initialize JDBC driver & connection pool
  Class.forName(derbyDriverClassname)
  ConnectionPool.singleton(dbURL, "me", "mine")

  implicit val session = AutoSession
}

object Database extends Database {
  def setupDB() = {
    if (!hasDBInitialize) {
      LeaderBoard.initializeTable()
    }
  }

  def hasDBInitialize: Boolean = {
    DB getTable "LeaderBoard" match {
      case Some(_) => true
      case None => false
    }
  }
}

