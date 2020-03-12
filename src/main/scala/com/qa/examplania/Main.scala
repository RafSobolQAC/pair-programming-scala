package com.qa.examplania

object Main {
  def main(args: Array[String]): Unit = {
    val exemplania = new Exemplania
    exemplania.taxMatcher(10000)
  }
}
