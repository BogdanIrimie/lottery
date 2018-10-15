package com.diwo.utils

import com.diwo.Ticket

class TicketEvaluator(winningTicket: Ticket) {

  /**
    * Evaluate the winning class for a given ticket.
    *
    * @param ticket for which to evaluate the winning class.
    * @return winning class.
    */
  def evaluateTicket(ticket: Ticket): WinningClass.Value = {
    val commonNumbers = ticket.numbers intersect winningTicket.numbers
    val commonStarNumbers = ticket.starNumbers intersect winningTicket.starNumbers

    (commonNumbers.size, commonStarNumbers.size) match {
      case (5,2) => WinningClass.Class1
      case (5,1) => WinningClass.Class2
      case (5,0) => WinningClass.Class3
      case (4,2) => WinningClass.Class4
      case (4,1) => WinningClass.Class5
      case (4,0) => WinningClass.Class6
      case (3,2) => WinningClass.Class7
      case (2,2) => WinningClass.Class8
      case (3,1) => WinningClass.Class9
      case (3,0) => WinningClass.Class10
      case (1,2) => WinningClass.Class11
      case (2,1) => WinningClass.Class12
      case (2,0) => WinningClass.Class13
      case (_, _) => WinningClass.Loser
    }
  }

}
