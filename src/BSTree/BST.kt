package BST

import Tree.Tree

class BST <T: Comparable<T>> : Tree<T> {

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

    var root: BSTElement<T>? = null
}