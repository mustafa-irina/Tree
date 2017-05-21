package BTree

import Tree.Tree
import java.util.*


class BT<T: Comparable<T>>: Iterable<BTElement<T>>, Tree<T> {

    var minDig = 3

    constructor(dig: Int = 3) {
        minDig = dig
    }

    private var root: BTElement<T>? = BTElement()


    override fun find (key: T) : Boolean {
        return findElement(key)
    }

    private fun findElement(key: T, element: BTElement<T>? = root): Boolean {

        if (element == null)
            return false

        var i = 0

        while (i < element.keys.size && key > element.keys[i]) {
            i++
        }

        if (i < element.keys.size && key == element.keys[i])
            return true

        if (element.isLeaf())
            return false

        return findElement(key, element.children[i])
    }


    private fun splitElement(parent: BTElement<T>, mediana: Int, sc: BTElement<T>?) {

        var brother = BTElement<T>()

        for (i in 0..minDig - 2) {
            brother.keys.add(0, sc!!.keys.removeAt(sc.keys.size - 1))
        }

        if (!sc!!.isLeaf()) {
            for (ind in 0..minDig - 1)
                brother.children.add(0, sc.children.removeAt(sc.children.size - 1))
        }

        parent.children.add(mediana + 1, brother)
        parent.keys.add(mediana, sc.keys.removeAt(minDig - 1))
    }


    override fun add(key: T) {
        if (root!!.keys.size == 2 * minDig - 1) {
            var newRoot = BTElement<T>()
            newRoot.children.add(root!!)
            splitElement(newRoot, 0, root)
            root = newRoot
        }

        add_general(root!!, key)
    }

    private fun add_general(currNode: BTElement<T>, key: T) {
        var i = currNode.keys.size - 1
        while (i >= 0 && key < currNode.keys[i])
            i--
        i++

        if (currNode.isLeaf()) {
            currNode.keys.add(i, key)
        } else {

            if (currNode.children[i].keys.size == 2 * minDig - 1) {
                splitElement(currNode, i, currNode.children[i])
                if (key > currNode.keys[i]) {
                    i++
                }
            }

            add_general(currNode.children[i], key)
        }
    }

    override fun delete(removeKey: T) {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun printTree() {
        var list: Queue<BTElement<T>> = LinkedList()
        var listChar: Queue<Char> = LinkedList()

        list.add(root)
        listChar.add('\n')

        var specSymbol = '\n'

        while (!list.isEmpty()) {
            var curNode = list.poll()

            for (key in curNode.keys)
                print(" $key ")

            if (!curNode.isLeaf()) {
                for (child in curNode.children) {
                    list.add(child)
                    listChar.add('|')
                }
            }
            if (listChar.peek() == '\n') {
                listChar.add(specSymbol)
                print(listChar.poll())
            }
            print(listChar.poll())
        }

    }

    override fun iterator(): Iterator<BTElement<T>> {
        return (object : Iterator<BTElement<T>>
        {
            var nodes : Queue<BTElement<T>> = LinkedList()

            init {
                if (!root!!.isEmpty())
                    nodes.add(root)
            }
            override fun hasNext(): Boolean {
                return !nodes.isEmpty()
            }

            override fun next(): BTElement<T> {
                var curNode = nodes.poll()
                curNode.children.forEach { child: BTElement<T> -> nodes.add(child) }
                return curNode
            }

        })
    }

}