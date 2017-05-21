package BST

import Tree.Tree
import java.util.*

class BST <T: Comparable<T>> : Iterable<BSTElement<T>>, Tree<T> {

    constructor()

    override fun find(searchKey: T): Boolean {
        var res: Boolean? = root?.find(searchKey)
        return (res != null) && res
    }

    override fun add(newKey: T){
        if (root?.add(newKey) == null)
            root = BSTElement(newKey)
    }
    override fun printTree(){
        root?.printTree()
    }
    override fun delete(removeKey: T) {
        root?.delete(removeKey)
        if (root?.key == removeKey)
            root = root?.parent
    }

    override fun iterator(): Iterator<BSTElement<T>> {
        return (object : Iterator<BSTElement<T>>
        {
            var nodes : Queue<BSTElement<T>> = LinkedList()

            init {
                if (root !== null)
                    nodes.add(root)
            }
            override fun hasNext(): Boolean {
                return !nodes.isEmpty()
            }

            override fun next(): BSTElement<T> {
                var curNode = nodes.poll()
                if (curNode.left !== null)
                    nodes.add(curNode.left)
                if (curNode.right !== null)
                    nodes.add(curNode.right)
                return curNode
            }
        })
    }

    var root: BSTElement<T>? = null
}