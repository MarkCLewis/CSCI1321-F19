package drmario

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.canvas.Canvas
import scalafx.scene.Scene
import scalafx.scene.input.KeyEvent
import scalafx.scene.input.KeyCode
import java.net.Socket
import java.io.ObjectOutputStream
import java.io.ObjectInputStream
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scalafx.application.Platform

object Client extends JFXApp {
  val canvas = new Canvas(800, 800)
  val renderer = new Renderer(canvas)
  
  val sock = new Socket("localhost", 8080)
  val out = new ObjectOutputStream(sock.getOutputStream())
  val in = new ObjectInputStream(sock.getInputStream())

  stage = new JFXApp.PrimaryStage {
		title = "Dr. Mario"
		scene = new Scene(800, 800) {
			content = canvas

			onKeyPressed = (ke: KeyEvent) => {
        out.writeInt(98)
        out.writeObject(ke.code)
				// ke.code match {
				// 	case KeyCode.Left => board.leftPressed()
				// 	case KeyCode.Right => board.rightPressed()
				// 	case KeyCode.Up => board.upPressed()
				// 	case KeyCode.Down => board.downPressed()
				// 	case _ => 
				// }
			}
			
			onKeyReleased = (ke: KeyEvent) => {
        out.writeInt(97)
        out.writeObject(ke.code)
				// ke.code match {
				// 	case KeyCode.Left => board.leftReleased()
				// 	case KeyCode.Right => board.rightReleased()
				// 	case KeyCode.Down => board.downReleased()
				// 	case _ => 
				// }
      }
      
      Future {
        while (true) {
          val pb = in.readObject() match {
            case board: PassableBoard => board
          }
          Platform.runLater(renderer.render(pb))
        }
      }
    }
  }
}