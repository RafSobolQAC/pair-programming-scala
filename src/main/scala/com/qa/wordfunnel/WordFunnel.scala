package com.qa.wordfunnel

import com.qa.wordfunnel.Main.wf

import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.concurrent.Future
import scala.io.Source

class WordFunnel {
  def getWordList(): Iterator[String] = {
    Source.fromResource("enable1.txt").getLines()
  }

  def getRegexFromWord(word: String): String = {
    var returner = new StringBuilder
    word.toCharArray.indices.foreach(index => {
      returner += word.charAt(index)
      returner += '?'
    }
    )
    returner.toString()
  }


  def getFilteredWordList(prevList: List[String], regex: String) = {
    prevList.filter(word => word.matches(regex))
  }

  def getNextWords(iterString: List[String], length: Int) = {
    iterString.filter(_.length <= length)
  }

  def getOnlyWordsOfLength(iterString: List[String], length: Int) = {
    iterString.filter(_.length == length)
  }

  /*
    how about:
    sort wordList by size
    e.g. word = abcde
    for each char in word:
    TreeThing.createOrAdd(a, TreeThing.createOrAdd(b, TreeThing.createOrAdd(c, TreeThing.createOrAdd(d, TreeThing.createOrAdd(e)))))
    TreeThing.update("abcd", "e") => adds "e"


   */



  def getWordsOverLengthThatHaveOneMoreLetter(depth: Int) = {
    getWordList().toArray.filter(el => el.length > depth).sliding(2).filter(el => {
      el(1).contains(el(0)) && el(1).length == el(0).length+1
    })
      .map(el => el(1))
      .filter(el => el.length != 0)
      .foreach(elem => {
      if (runner(elem) == depth) println("Found! It's "+elem)
    })
  }

  def findWordsWithFunnel(depth: Int) = {
    val allWords = getWordList().toArray
    allWords.filter(el => el.length > depth).foreach(el => {
      println(el)
      if (runner(el) == depth) println("Found! It's " + el)
    }
    )
  }

  def wordFinder(previousMax: Int, initLength: Int, word: String, step: Int, listWords: List[String], steps: mutable.HashSet[Int]): Int = {
    var nowMax = 0
    if (step > nowMax) nowMax = step
    if (step == initLength - 1 || word == "") {
    } else {
      if (getOnlyWordsOfLength(listWords, initLength - step).nonEmpty) {
        getNextWords(listWords, initLength - step)
          .filter(el => el.length == initLength - step)
          .foreach(smallWord => {
            wordFinder(
              nowMax, initLength, smallWord, step + 1,
              getNextWords(
                getFilteredWordList(listWords,
                  getRegexFromWord(smallWord)
                ), initLength - step
              ), steps
            )

          }
          )

      }
    }

    steps += step
    step
  }

  def getMaxStep(steps: mutable.HashSet[Int]) = {
    //    steps.reduce((a, b) => if (a > b) a else b)
    steps.max
  }

  def runner(word: String) = {
    val steps = new mutable.HashSet[Int]
    wordFinder(0, word.length, word, 1, getFilteredWordList(getWordList().toList,getRegexFromWord(word)), steps)
    getMaxStep(steps)


  }
}
