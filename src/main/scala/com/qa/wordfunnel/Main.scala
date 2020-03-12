package com.qa.wordfunnel

import scala.collection.mutable

object Main extends App {
  val wf = new WordFunnel
  //  wf.getFilteredWordList(wf.getRegexFromWord("princesses")).foreach(println)
//  println("-------------------------")
//  wf.wordFinder(0, 10,"princesses",1,wf.getFilteredWordList(wf.getRegexFromWord("princesses")))
//  println(wf.runner("princesses"))
//  println(wf.findWordsWithFunnel(10))
//  println(wf.getWordsOverLengthThatHaveOneMoreLetter(10))
//  val wordList = wf.getWordList().toList.sortBy(_.length)
//  (1 to 30).foreach(num => println(wordList(num)))
  val treeThingA = TreeThing.apply('a')
  val treeThings = new TreeThings(mutable.HashSet(treeThingA))
  val charArray = "bb".toCharArray
  val treeThingB = TreeThing.apply('b', treeThings)

  val all = TreeThings(mutable.HashSet(treeThingA,treeThingB))


  println(TreeThing.getTreeToUpdate(TreeThings(mutable.HashSet()), charArray, all, Some(treeThingB)))


}