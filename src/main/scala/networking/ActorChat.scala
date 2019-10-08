package networking
import akka.actor.ActorSystem
import akka.actor.Props
import java.net.ServerSocket
import java.io.PrintStream
import java.io.BufferedReader
import java.io.InputStreamReader
import scala.concurrent.Future
import scala.concurrent.duration._

object ActorChat extends App {
  val system = ActorSystem("Chat")

  val manager = system.actorOf(Props[ChatManager], "manager")

  implicit val ec = system.dispatcher
  
  system.scheduler.schedule(1.second, 0.1.second, manager, ChatManager.CheckAllInput)

  val ss = new ServerSocket(4040)
  while (true) {
    val sock = ss.accept()
    Future {
      val out = new PrintStream(sock.getOutputStream())
      out.println("What is your name?")
      val in = new BufferedReader(new InputStreamReader(sock.getInputStream()))
      val name = in.readLine()
      println(name + " has connected.")
      manager ! ChatManager.NewChatter(name, sock, out, in)
    }
  }
}
