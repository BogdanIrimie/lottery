package com.diwo

/**
  * Lottery ticket. The ticket is made of two sets: _numbers and _starNumbers.
  */
class Ticket {
  private var _numbers: Set[Int] = _
  private var _starNumbers: Set[Int] = _

  def numbers = _numbers
  def numbers_= (num: Set[Int]) =
    if (num.size > 10) throw new Exception("To many numbers") else _numbers = num

  def starNumbers = _starNumbers
  def starNumbers_= (starNum: Set[Int]) =
    if (starNum.size > 5) throw new Exception("To many star numbers") else _starNumbers = starNum

  def this(numbers: Set[Int], starNumbers: Set[Int]) {
    this()
    _numbers = numbers
    _starNumbers = starNumbers
  }

  /**
    * Generate all normal tickets from a system ticket.
    *
    * @return list with all possible normal tickets generated from a system ticket.
    */
  def generateNormalTickets(): List[(List[Int], List[Int])] = {
    val numberCombinations = _numbers.subsets(5).map(_.toList).toList
    val starNumberCombinations = _starNumbers.subsets(2).map(_.toList).toList
    val tickets: List[(List[Int], List[Int])] = numberCombinations.flatMap(x => starNumberCombinations.map(y => (x, y)))

    tickets
  }

  def addNumber(number: Int): Unit = _numbers += number
  def addStarNumber(number: Int): Unit = _starNumbers += number
  def getTicketNumbers(): Set[Int] = _numbers
  def getTicketStarNumbers(): Set[Int] = _starNumbers
}
