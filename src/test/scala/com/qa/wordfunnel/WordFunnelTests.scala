package com.qa.wordfunnel

import com.qa.exemplania.UnitSpec
import com.qa.wordfunnel.Main.wf

import scala.io.Source

class WordFunnelTests extends UnitSpec {

  val funnalia = new WordFunnel

  "The getWordList function" should "return an Iterator[String]" in {
    assert(funnalia.getWordList().isInstanceOf[Iterator[String]])
  }

  "The getRegexFromWord function" should "return a string" in {
    assert(funnalia.getRegexFromWord("AWord").isInstanceOf[String])
  }

  it should "return the input with the chars separated by question marks" in {
    assert(funnalia.getRegexFromWord("abc") == "a?b?c?")

  }

  "getFilterWordList" should "return a string" in {
    assert(funnalia.getFilteredWordList("a").isInstanceOf[Iterator[String]])
  }

  "getNextWord" should "return a list of strings" in {
    assert(funnalia.getNextWords(funnalia.getFilteredWordList(funnalia.getRegexFromWord("gnash")), 1).isInstanceOf[Iterator[String]])
  }

  "getOnlyWordsOfLength" should "return an iterator of strings" in {
    assert(funnalia.getOnlyWordsOfLength(funnalia.getFilteredWordList(funnalia.getRegexFromWord("gnash")), 1).isInstanceOf[Iterator[String]])
  }

  "depthGetter" should "return the depth of a given iteration" in {
    assert(funnalia.depthGetter(3,2).isInstanceOf[Int])
  }

//  "wordFinder" should "return the biggest depth" in {
//    assert(funnalia.wordFinder(5, "gnash", 1).isInstanceOf[Int])
//  }

}
