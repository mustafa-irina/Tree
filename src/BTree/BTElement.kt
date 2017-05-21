package BTree

import java.util.*

class BTElement<Key : Comparable<Key>> {

    var keys = ArrayList<Key> ()
    var children = ArrayList<BTElement<Key>> ()

    fun isLeaf () = children.size == 0

    fun isEmpty () = keys.size == 0


}
