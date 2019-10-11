package drmario

import scalafx.scene.canvas.Canvas
import scalafx.scene.paint.Color

class Renderer(canvas: Canvas) {
  val gc = canvas.graphicsContext2D

  def render(board: PassableBoard): Unit = {
    gc.fill = Color.Black
    gc.fillRect(0, 0, 800, 800)
    for (elem <- board.elements; cell <- elem.cells) {
      drawCell(cell)
    }
    for (cell <- board.currentPill.cells) {
      drawCell(cell)
    }
  }

  def drawCell(cell: PassableCell): Unit = {
    val color = cell.color match {
      case DMColor.Red => Color.Red
      case DMColor.Yellow => Color.Yellow
      case DMColor.Blue => Color.Blue
    }
    gc.fill = color
    cell.style match {
      case 0 =>  // Virus
        gc.fillOval(cell.x*20, cell.y*20, 20, 20)
      case 1 =>  // Pill
        gc.fillRect(cell.x*20, cell.y*20, 20, 20)
    }  }
  
}