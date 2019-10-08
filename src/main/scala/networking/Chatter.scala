package networking
import akka.actor.Actor
import java.net.Socket
import java.io.PrintStream
import java.io.BufferedReader
import networking.Chatter._

class Chatter(name: String, sock: Socket, out: PrintStream, in: BufferedReader) extends Actor {
  def receive = {
    case CheckInput =>
      if (in.ready()) {
        val message = in.readLine()
        context.parent ! ChatManager.SendToAll(name + ": " + message)
      }
    case PrintMessage(msg) =>
      out.println(msg)
    case m => println("Unhandled message in Chatter: "+m)
  }
}

object Chatter {
  case object CheckInput
  case class PrintMessage(msg: String)
}