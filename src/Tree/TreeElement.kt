package Tree

abstract class TreeElement<T: Comparable<T>> {

    constructor(newKey: T) {
        key = newKey
    }

    abstract fun find(searchKey: T): Boolean
    abstract fun add(addKey: T)
    abstract fun delete(removeKey: T)
    abstract fun printTree()
    abstract fun removeChild(newKey: T)
    var key: T
}