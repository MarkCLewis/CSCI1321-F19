package actors
import akka.actor.Actor

class Floaty extends Actor {
  def receive = {
    case m => println("Unhandled message in floaty: " + m)
  }
}

object Floaty {
  
}