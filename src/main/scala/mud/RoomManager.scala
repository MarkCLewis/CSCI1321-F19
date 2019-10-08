package mud
import akka.actor.Actor
import akka.actor.ActorRef
import akka.actor.Props

class RoomManager extends Actor {

	import RoomManager._
  def receive = {
		case AddPlayerToRoom(player, keyword) =>
			player ! Player.TakeExit(rooms.get(keyword))
    case m => println("Unhandled message in RoomSupervisor: " + m)
  }

	val rooms = readRooms()
	for (child <- context.children) child ! Room.LinkExits(rooms)

	def readRooms(): Map[String, ActorRef] = {
		val xmlData = xml.XML.loadFile("map.xml")
		(xmlData \ "room").map(readRoom).toMap
  }
  
	def readRoom(node: xml.Node): (String, ActorRef) = {
		val keyword = (node \ "@keyword").text
		val name = (node \ "@name").text
		val description = (node \ "description").text.trim
		val exits = (node \ "exits").text.split(",")
		val item = (node \ "item").map(n => Item((n \ "@name").text,n.text.trim)).toList
		keyword -> context.actorOf(Props(new Room(keyword, name,description,exits,item)), keyword)
	}
}

object RoomManager {
	case class AddPlayerToRoom(player: ActorRef, keyword: String)
}