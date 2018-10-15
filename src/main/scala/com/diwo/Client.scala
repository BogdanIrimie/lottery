package com.diwo

import com.diwo.utils.{FileHelper, TicketEvaluator, WinningClass}

object Client extends App {

  // read system ticket from file
  val systemTicket: Ticket = FileHelper.readTicketFromFile()

  // generate all normal tickets from a system ticket
  val tickets = systemTicket.generateNormalTickets()//TicketFromSysTicketGenerator.generate(systemTicket)

  // write normal tickets to file
  FileHelper.writeNormalTicketsToFile(tickets)

  // read winning ticket from file
  val winningTicket: Ticket = FileHelper.readWiningTicket()

  val ticketEvaluator = new TicketEvaluator(winningTicket);
  var results: Map[WinningClass.Value, Int] = Map()

  // compute lottery results
  FileHelper.readParticipatingTickers().foreach(ticket => {
    val winningClass = ticketEvaluator.evaluateTicket(ticket);
    results += (winningClass -> (results.getOrElse(winningClass, 0) + 1))
  })

  // write lottery result to file
  FileHelper.writeLotteryResultsToFile(results)
}
