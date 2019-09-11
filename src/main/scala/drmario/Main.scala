package drmario

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.canvas.Canvas
import scalafx.animation.AnimationTimer
import scalafx.scene.input.KeyEvent
import scalafx.scene.input.KeyCode

object Main extends JFXApp {
	val canvas = new Canvas(800, 800)
	val renderer = new Renderer(canvas)
	val board = new Board
	
	stage = new JFXApp.PrimaryStage {
		title = "Dr. Mario"
		scene = new Scene(800, 800) {
			content = canvas

			onKeyPressed = (ke: KeyEvent) => {
				println("Key pressed")
				ke.code match {
					case KeyCode.Left =>
					case _ =>
				}
			}
			
			val timer = AnimationTimer(time => {
				board.update()
				renderer.render(board)
			})
			timer.start()
		}
	}
}