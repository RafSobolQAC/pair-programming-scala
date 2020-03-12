package com.qa.examplania

class Exemplania {

  val map = Map(
    0 -> 0.00,
    10000 -> 0.10,
    30000 -> 0.25,
    100000 -> 0.40
  )
  /* first ->
    have a function where:
      getIncomeTaxed(income: Int, map: Map) = {
        for each entry in map
          if (map.key < income) then take previous map.key
          if none matched take last map.key
        then apply that map.key to getIncomeAtBracket(income)
        getIncomeTaxed(max value at previous key)
      }
   */

  def taxMatcher(amount: Int): Double = amount match {
    case x if x <= 10000 => 0.00
    case y if y > 10000 && y <= 30000 => 0.10
    case z if z > 30000 && z <= 100000 => 0.25
    case _ => 0.40
  }

  def getIncomeAtBracket(income: Int): Int = income match {
    case x if x <= 10000 => 0
    case y if y > 10000 && y <= 30000 => y - 10000
    case z if z > 30000 && z <= 100000 => z - 30000
    case w if w > 100000 => w - 100000
  }

  def taxCalculator(income: Int, taxSoFar: Int): Int = {
    if (income > 10000) {
      taxCalculator(income - getIncomeAtBracket(income), taxSoFar + (taxMatcher(income) * getIncomeAtBracket(income)).toInt)
    } else {
      taxSoFar
    }


  }

  def delta(toDelta: Double, ot: Double): Boolean = {
    if (toDelta > ot * 1.00001 || toDelta < ot * 0.99999)
      false
    else
      true
  }

  def incomeCalculator(ot: Double): Int = {
    var income = 10000
    if (ot != 0) {
      while (!delta(taxCalculator(income, 0).toDouble / income, ot)) {
        income += 1
      }
    }
    income
  }
}


