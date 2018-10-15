package com.diwo.utils

import java.io.{File, PrintWriter}
import com.diwo.Ticket
import scala.io.Source

/**
  * Write and read data from files.
  */
object FileHelper {

  private final val SYSTEM_TICKET_FILE = "systemTicketNumbers.txt"
  private final val NORMAL_TICKETS_FILE = "normalTickets.txt"
  private final val WINING_NUMBERS_FILE = "winingTicket.txt"
  private final val PARTICIPATING_NUMBERS_FILE = "participatingNumbers.txt"
  private final val LOTTERY_RESULTS = "lotteryResults.txt"

  /**
    * Read ticket data from file.
    * The format should be coma separated numbers minus coma separated star numbers.
    * E.g. 1,2,3,4,5 - 1,2
    *
    * @return a ticket object
    */
  def readTicketFromFile(): Ticket = {
    Source.fromFile(SYSTEM_TICKET_FILE).getLines.map { line =>
      val ticket = line.split("-")
      val numbers = ticket(0).split(",").map(_.trim.toInt).toSet
      val starNumbers = ticket(1).split(",").map(_.trim.toInt).toSet

      new Ticket(numbers, starNumbers)
    }.toList.head
  }

  /**
    * Writes the normal tickets to a file.
    *
    * @param tickets is a list with tuples of numbers and starNumbers.
    */
  def writeNormalTicketsToFile(tickets: List[(List[Int], List[Int])]): Unit = {
    val writer = new PrintWriter(new File(NORMAL_TICKETS_FILE))
    tickets.foreach(ticket =>
      writer.write(s"${ticket._1.mkString(",")} - ${ticket._2.mkString(",")}\n")
    )
    writer.close()
  }

  /**
    * Read winning ticket from a file.
    *
    * @return ticket object representing the winning ticket.
    */
  def readWiningTicket(): Ticket = {
    Source.fromFile(WINING_NUMBERS_FILE).getLines.map { line =>
      val ticket = line.split("-")
      val numbers = ticket(0).split(",").map(_.trim.toInt).toSet
      val starNumbers = ticket(1).split(",").map(_.trim.toInt).toSet

      new Ticket(numbers, starNumbers)
    }.toList.head
  }

  /**
    * Read all participating tickets from a file.
    * The format should be coma separated numbers minus coma separated star numbers.
    *
    * @return list of ticket object representing played numbers.
    */
  def readParticipatingTickers(): List[Ticket] = {
    Source.fromFile(PARTICIPATING_NUMBERS_FILE).getLines.map { line =>
      val ticket = line.split("-")
      val numbers = ticket(0).split(",").map(_.trim.toInt).toSet
      val starNumbers = ticket(1).split(",").map(_.trim.toInt).toSet

      new Ticket(numbers, starNumbers)
    }.toList
  }

  /**
    * Writes lottery results in a file.
    *
    * @param results
    */
  def writeLotteryResultsToFile(results: Map[WinningClass.Value, Int]): Unit = {
    val writer = new PrintWriter(new File(LOTTERY_RESULTS))
    results.foreach(result =>
      writer.write(s"${result._1} has ${result._2} tickets.\n")
    )
    writer.close()
  }

}
