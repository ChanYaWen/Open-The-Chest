package yw.makery.address.model

import scalikejdbc._
import scalafx.beans.property.{IntegerProperty, StringProperty}
import yw.makery.address.util.Database

class LeaderBoard(val usernameS: String = "", val attemptS: Int = 0) extends Database {
  val username = new StringProperty(this, "username", usernameS)
  val attempt = new IntegerProperty(this, "attempts", attemptS)

  def save(): Unit = {
    DB localTx { implicit session =>
      sql"""
        INSERT INTO leaderboard (username, attempt)
        VALUES (${username.value}, ${attempt.value})
      """.update.apply()
    }
  }
}

object LeaderBoard extends Database {
  def apply(username: String, attempt: Int): LeaderBoard = {
    new LeaderBoard(username, attempt)
  }

  def initializeTable(): Unit = {
    DB autoCommit { implicit session =>
      sql"""
        CREATE TABLE leaderboard (
          username VARCHAR(64),
          attempt INT NOT NULL
        )
      """.execute.apply()
    }
  }
  def fetchTop10(): Seq[LeaderBoard] = {
    DB readOnly { implicit session =>
      sql"""
      SELECT username, attempt
      FROM leaderboard
      ORDER BY attempt ASC
      FETCH FIRST 10 ROWS ONLY
    """.map(rs =>  LeaderBoard(
        username = rs.string("username"),
        attempt = rs.int("attempt")
      )).list.apply()
    }
  }
}