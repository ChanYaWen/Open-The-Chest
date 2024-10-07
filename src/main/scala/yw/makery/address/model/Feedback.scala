package yw.makery.address.model

import scalafx.beans.property.StringProperty

class Feedback(guess: String, feedback: String) {
  val guessProperty = new StringProperty(this, "guess", guess)
  val feedbackProperty = new StringProperty(this, "feedback", feedback)

  def getEnter: String = guessProperty.get()
  def getFeedback: String = feedbackProperty.get()
}