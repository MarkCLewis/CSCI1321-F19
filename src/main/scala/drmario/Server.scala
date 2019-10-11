package drmario
import java.net.ServerSocket
import java.io.ObjectOutputStream
import java.io.ObjectInputStream

object Server extends App {
  val board = new Board
  
  val ss = new ServerSocket(8080)
  val sock = ss.accept()
  val in = new ObjectInputStream(sock.getInputStream())
  val out = new ObjectOutputStream(sock.getOutputStream())

  var lastTime = -1L
  while(true) {
    val time = System.nanoTime()
    if(lastTime > 0) {
      val delay = (time - lastTime)/1e9
      board.update(delay)
      val pb = board.makePassable
      // out.writeInt(99)
      out.writeObject(pb)
    }
    lastTime = time
  }
}