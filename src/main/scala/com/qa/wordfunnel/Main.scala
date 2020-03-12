package com.qa.wordfunnel

object Main extends App {
  val wf = new WordFunnel
  wf.getFilteredWordList(wf.getRegexFromWord("gnash")).foreach(println)
  println("-------------------------")
  println(wf.wordFinder(5,"gnash",1,wf.getFilteredWordList(wf.getRegexFromWord("gnash"))))
}
