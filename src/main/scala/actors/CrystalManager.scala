package actors
import akka.actor.Actor
import scalafx.scene.image.WritableImage

class CrystalManager(img: WritableImage) extends Actor {
  val reader = img.pixelReader
  val writer = img.pixelWriter
  def receive = {
    case m => println("Unhandled message in manager: " + m)
  }
}

object CrystalManager {
  
}