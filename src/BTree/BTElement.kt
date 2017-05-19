package BTree

import java.util.*


class BTElement<Key : Comparable<Key>>(){

    var keys = ArrayList<Key> ()
    var children = ArrayList<BTElement<Key>> ()

    fun isLeaf () = children.size == 0

    fun isEmpty () = keys.size == 0

//    fun find(searchKey: Key): Boolean {
//        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }

//    fun add(addKey: Key) {
//        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//    override fun delete(removeKey: Key) {
//        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//    override fun printTree() {
//        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//    override fun removeChild(newKey: Key) {
//        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
}
