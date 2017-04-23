package RBT

import Tree.Tree

class RBT<T: Comparable<T>> : Tree<T>{
    
    constructor()

    override fun add(newKey: T) {
        if (root?.add(newKey) == null) {
            root = RBTElement(newKey)
            root!!.color = RBTElement.Color.Black
        } else if (root?.parent != null) {
            root = root?.parent
        }
    }
    override fun delete(removeKey: T) {}
    override fun find(searchKey: T): Boolean {
        var res: Boolean? = root?.find(searchKey)
        return (res != null) && res
    }
    override fun printTree() {
        root?.printTree()
    }

    var root: RBTElement<T>? = null
}