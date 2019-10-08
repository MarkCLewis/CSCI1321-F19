package networking
import akka.actor.Actor
import java.net.Socket
import java.io.PrintStream
import java.io.BufferedReader
import akka.actor.Props

class ChatManager extends Actor {
  import ChatManager._
  def receive = {
    case NewChatter(name, sock, out, in) =>
      context.actorOf(Props(new Chatter(name, sock, out, in)), name)
    case CheckAllInput =>
      for (child <- context.children) child ! Chatter.CheckInput
    case SendToAll(msg) =>
      for (child <- context.children) child ! Chatter.PrintMessage(msg)
    case m => println("Unhandled message in ChatManager: "+m)
  }
}

object ChatManager {
  case class NewChatter(name: String, sock: Socket, out: PrintStream, in: BufferedReader)
  case object CheckAllInput
  case class SendToAll(msg: String)
}