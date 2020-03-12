package com.qa.exemplania
import com.qa.examplania.Exemplania
import org.scalatest._

class ExemplaniaTest extends UnitSpec {
  val exemplania = new Exemplania

  "class Exemplania" should "exist" in {
    assert(exemplania.isInstanceOf[Exemplania])
  }

  "the function taxMatcher" should "return tax bracket for input amount" in {
    assert(exemplania.taxMatcher(10000) == 0.00)
    assert(exemplania.taxMatcher(20000) == 0.10)
    assert(exemplania.taxMatcher(70000) == 0.25)
    assert(exemplania.taxMatcher(300000) == 0.40)
  }

  "the function getIncomeAtBracket" should "return amount taxed at bracket" in {

    assert(exemplania.getIncomeAtBracket(150000) == 50000)
    assert(exemplania.getIncomeAtBracket(70000) == 40000)
    assert(exemplania.getIncomeAtBracket(20000) == 10000)
    assert(exemplania.getIncomeAtBracket(5000) == 0)
  }

  "the taxCalculator function" should "return the tax" in {
    assert(exemplania.taxCalculator(56789,0) == 8697)
    assert(exemplania.taxCalculator(12000,0) == 200)
    assert(exemplania.taxCalculator(1234567,0) == 473326)
    assert(exemplania.taxCalculator(10010,0) == 1)

     }

  def isBetween(value: Int, expected: Int): Boolean = {
    (value-expected).abs < expected*0.0001
  }

  "incomeCalculator" should " return the income taxed at an overall tax-rate" in {
    assert(isBetween(exemplania.incomeCalculator(0.32),256250))
    assert(isBetween(exemplania.incomeCalculator(0.06),25000))
    assert(isBetween(exemplania.incomeCalculator(0.0),10000))
    assert(isBetween(exemplania.incomeCalculator(0.09), 34375))
  }
}



