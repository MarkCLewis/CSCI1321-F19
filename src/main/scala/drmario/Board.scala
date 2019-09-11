package drmario

class Board {
  private var _elements = List.fill[BoardElement](10)(new Virus(util.Random.nextInt(8), 3+util.Random.nextInt(13), DMColor.randomColor()))
  private var _currentPill = new Pill(List(
    new PillPiece(3, 0, DMColor.Red),
    new PillPiece(4, 0, DMColor.Blue)
  ))

  def elements = _elements
  def currentPill = _currentPill

  def update(): Unit = {
    
  }
}