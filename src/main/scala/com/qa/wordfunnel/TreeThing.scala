package com.qa.wordfunnel

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

object TreeThing {

  def apply(character: Char, pointToNext: TreeThings = null): TreeThing = new TreeThing(character, pointToNext)

//  def update(toAdd: Char, nestedTreeThing: TreeThing, treeThings: TreeThings): TreeThings = {

//  }

  def getTreeToUpdate(path: TreeThings, charArray: Array[Char], treeThings: TreeThings, currentTree: Option[TreeThing] = null): Option[TreeThing] = {
    if (currentTree != null && currentTree.isEmpty) None
    else if (charArray.length == 0) {
      Some(currentTree.get)
    }
    else {
      getTreeToUpdate(TreeThings(path.treeThings.addOne(currentTree.get)), charArray.slice(1, charArray.length), currentTree.get.next, treeThings.treeThings.find(el => el.character == charArray.head))
    }
  }
}

case class TreeThing(character: Char, next: TreeThings = null)

case class TreeThings(treeThings: mutable.HashSet[TreeThing])
