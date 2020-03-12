package com.qa.wordfunnel

import scala.io.Source

class WordFunnel {
  def getWordList(): Iterator[String] = {
    Source.fromResource("enable1.txt").getLines()
  }

  //input: "LETTERS"
  //return: "L?E?T?T?E?R?S?"

  def getRegexFromWord(word: String): String = {
    var returner = new StringBuilder
    word.toCharArray.indices.foreach(index => {
      returner += word.charAt(index)
      returner += '?'
    }
    )
    returner.toString()
  }

  def getFilteredWordList(regex: String) = {
    getWordList().filter(word => word.matches(regex)).toList
  }

  def getNextWords(iterString: List[String], length: Int) = {
    iterString.filter(_.length <= length).toList
  }

  def getOnlyWordsOfLength(iterString: List[String], length: Int) = {
    iterString.filter(_.length == length).toList
  }

  def depthGetter(initLength: Int, length: Int): Int = {
    initLength - length + 1
  }

  def wordFinder(initLength: Int, word: String, step: Int, listWords: List[String]): Int = {
    if (step == initLength || word == "") {
    } else {
      if (getOnlyWordsOfLength(listWords, initLength - step).nonEmpty) {
        getNextWords(listWords, initLength - step)
          .map(smallWord => {
            wordFinder(
              initLength, smallWord, step + 1,
              getNextWords(
                getFilteredWordList(
                  getRegexFromWord(smallWord)
                ), initLength - step
              )
            )
          }
          )

      }
    }
    println(step)
    step
  }

}
