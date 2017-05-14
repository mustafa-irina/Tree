package BST

import Tree.TreeElement

class BSTElement<T: Comparable<T>> : TreeElement<T> {

    constructor(newKey: T) : super(newKey)

    override fun find(searchKey: T): Boolean {
        if (searchKey > key)
            if (right == null)
                return false
            else
                return right!!.find(searchKey)
        if (searchKey < key)
            if (left == null)
                return false
            else
                return left!!.find(searchKey)
        return true
    }
    override fun add(addKey: T) {
        if (addKey > key)
            if (right?.add(addKey) == null) {
                right = BSTElement(addKey)
                right?.parent = this
            }
        if (addKey < key)
            if (left?.add(addKey) == null) {
                left = BSTElement(addKey)
                left?.parent = this
            }
    }

    override fun removeChild(newKey: T) {
        if (left?.key == newKey)
            left = null
        if (right?.key == newKey)
            right = null
    }

    override fun delete(removeKey: T) {
        if (key > removeKey)
            left?.delete(removeKey)
        if (key < removeKey)
            right?.delete(removeKey)
        if (key == removeKey) {
            if (right == null && left == null)
                 parent?.removeChild(removeKey) //если это был корень, то нас выкинет на следующее условие в BST
            else if (right == null) {
                left?.parent = parent
                if (parent == null) {
                    parent = left
                    return // выкинет обратно к BST и как раз выполнится следующее условие (коммент 2)
                }
                if (left!!.key > parent!!.key)
                    parent?.right = left
                else
                    parent?.left = left
            } else {
                if (right?.left == null) {
                    right?.left = left
                    right?.parent = parent
                    left?.parent = right
                    if (parent == null) {
                        parent = right
                        return // см. коммет 2
                    }
                    if (parent!!.key > right!!.key)
                       parent?.left = right
                    else
                       parent?.right = right
                    return
                }
                var minRight: BSTElement<T>? = right
                while (minRight?.left != null) {
                    minRight = minRight?.left
                }
                minRight?.right?.parent = minRight?.parent
                minRight?.parent?.left = minRight?.right
                minRight?.left = left
                minRight?.parent = parent
                minRight?.right = right
                right?.parent = minRight
                if (parent == null) {
                    parent = minRight
                    return // см. коммент 2
                }
                if (minRight!!.key > parent!!.key)
                    parent?.right = minRight
                else 
                    parent?.left = minRight

            }
        }

    }

    override fun printTree() {
        print("(")
        left?.printTree()
        print(" $key ")
        right?.printTree()
        print(")")
    }

    var left: BSTElement<T>? = null
    var right: BSTElement<T>? = null
    var parent: BSTElement<T>? = null
}