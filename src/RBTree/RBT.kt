package RBT

import Tree.Tree
import java.util.*

class RBT<T: Comparable<T>> : Iterable<RBTElement<T>>, Tree<T>{
    
    constructor()

    override fun add(newKey: T) {
        if (root?.add(newKey) == null) {
            root = RBTElement(newKey)
            root!!.color = RBTElement.Color.Black
        } else if (root?.parent != null) {
            root = root?.parent
        }
    }

    override fun delete(removeKey: T) {
        root?.delete(removeKey)
        if (root?.key == removeKey)
            root = root?.parent
    }

    override fun find(searchKey: T): Boolean {
        var res: Boolean? = root?.find(searchKey)
        return (res != null) && res
    }

    override fun printTree() {
        root?.printTree()
    }

    override fun iterator(): Iterator<RBTElement<T>> {
        return (object : Iterator<RBTElement<T>>
        {
            var nodes : Queue<RBTElement<T>> = LinkedList()

            init {
                if (root !== null)
                    nodes.add(root)
            }
            override fun hasNext(): Boolean {
                return !nodes.isEmpty()
            }

            override fun next(): RBTElement<T> {
                var curNode = nodes.poll()
                if (curNode.left !== null)
                    nodes.add(curNode.left)
                if (curNode.right !== null)
                    nodes.add(curNode.right)
                return curNode
            }
        })
    }

    var root: RBTElement<T>? = null
}