package drmario
import java.net.ServerSocket
import java.io.ObjectOutputStream
import java.io.ObjectInputStream
import scalafx.scene.input.KeyCode

object Server extends App {
  val board = new Board
  
  val ss = new ServerSocket(8080)
  val sock = ss.accept()
  val in = new ObjectInputStream(sock.getInputStream())
  val out = new ObjectOutputStream(sock.getOutputStream())

  var lastTime = -1L
  val sendInterval = 0.05
  var sendDelay = 0.0
  while(true) {
    if (in.available() > 0) {
      val code = in.readInt()
      val key = in.readObject
      if(code == 98) {
				key match {
					case KeyEnums.Left => board.leftPressed()
					case KeyEnums.Right => board.rightPressed()
					case KeyEnums.Up => board.upPressed()
					case KeyEnums.Down => board.downPressed()
					case _ => 
				}
      } else {
				key match {
					case KeyEnums.Left => board.leftReleased()
					case KeyEnums.Right => board.rightReleased()
					case KeyEnums.Down => board.downReleased()
					case _ => 
				}
      }
    }
    val time = System.nanoTime()
    if(lastTime > 0) {
      val delay = (time - lastTime)/1e9
      sendDelay += delay
      board.update(delay)
      if (sendDelay >= sendInterval) {
        val pb = board.makePassable
        // out.writeInt(99)
        out.writeObject(pb)
        sendDelay = 0.0
      }
    }
    lastTime = time
  }
}