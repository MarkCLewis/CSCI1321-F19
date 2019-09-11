package drmario

class Pill(pieces: List[PillPiece]) extends BoardElement {
  def cells: List[Cell] = pieces
}