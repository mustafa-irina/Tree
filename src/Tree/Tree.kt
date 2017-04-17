package Tree

abstract class Tree<T: Comparable<T>> {

    abstract fun add(newKey: T)
    abstract fun delete(removeKey: T)
    abstract fun find(searchKey: T): Boolean
    abstract fun printTree()
}
