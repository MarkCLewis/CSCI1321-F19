package actors
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.image.WritableImage
import scalafx.scene.image.ImageView
import akka.actor.ActorSystem

object Crystals extends JFXApp {
  val system = ActorSystem("Crystals")
  val img = new WritableImage(800, 800)


  stage = new JFXApp.PrimaryStage {
    title = "Crystals!"
    scene = new Scene(800, 800) {
      val view = new ImageView(img)
      content = view
    }
  }
}