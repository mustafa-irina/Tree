package RBT

import Tree.TreeElement

class RBTElement<T: Comparable<T>> : TreeElement<T> {

    constructor(newKey: T): super(newKey)

    enum class Color {Red, Black}

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
    private fun brother(): RBTElement<T>?{
        if (parent?.left == this)
            return parent?.right
        if (parent?.right == this)
            return parent?.left
        return null
    }
    override fun add(addKey: T){
        if (addKey < key){
            if (left?.add(addKey) == null){
                left = RBTElement(addKey)
                left?.parent = this
                left?.color = RBTElement.Color.Red
                return left!!.balance()
            }
        }
        if (addKey > key){
            if (right?.add(addKey) == null){
                right = RBTElement(addKey)
                right?.parent = this
                right?.color = RBTElement.Color.Red
                return right!!.balance()
            }
        }
    }
    private fun rotateLeft(){
        var rightSon: RBTElement<T> = right!!
        right = rightSon.left
        rightSon.left?.parent = this

        rightSon.parent = parent
        rightSon.left = this
        parent = rightSon

        if (rightSon.parent?.left == this)
            rightSon.parent?.left = rightSon
        else
            rightSon.parent?.right = rightSon
    }
    private fun rotateRight(){
        var leftSon: RBTElement<T> = left!!
        left = leftSon.right
        leftSon.right?.parent = this

        leftSon.parent = parent
        leftSon.right = this
        parent = leftSon

        if (leftSon.parent?.left == this)
            leftSon.parent?.left = leftSon
        else
            leftSon.parent?.right = leftSon
    }
    private fun balance(){
        if (parent == null) {
            color = Color.Black
            return
        }
        if (parent?.color == RBTElement.Color.Black)
            return
        if (parent!!.brother()?.color == RBTElement.Color.Red) {
            parent!!.color = RBTElement.Color.Black
            parent!!.brother()?.color = RBTElement.Color.Black
            parent!!.parent?.color = RBTElement.Color.Red
            parent!!.parent?.balance()
        }
        else {
            if (parent!!.parent?.left == parent) {
                if (parent!!.left == this) {
                    parent!!.color = RBTElement.Color.Black
                    parent!!.parent?.color = RBTElement.Color.Red
                    parent!!.parent!!.rotateRight()
                }
                else {
                    parent!!.rotateLeft()
                    parent!!.right!!.color = RBTElement.Color.Black
                    parent?.color = RBTElement.Color.Red
                    parent!!.rotateRight()
                }
            }
            else {
                if (parent!!.right == this) {
                    parent!!.color = RBTElement.Color.Black
                    parent!!.parent?.color = RBTElement.Color.Red
                    parent!!.parent!!.rotateLeft()
                }
                else {
                    parent!!.rotateRight()
                    color = RBTElement.Color.Black
                    parent?.color = RBTElement.Color.Red
                    parent!!.rotateLeft()
                }
            }
        }
    }
    override fun delete(removeKey: T){}
    override fun printTree(){
        print("(")
        left?.printTree()
        var c: Char
        if (color == Color.Black)
            c = 'B'
        else
            c = 'R'
        print(" $key$c ")
        right?.printTree()
        print(")")
    }
    override fun removeChild(newKey: T){}

    var left: RBTElement<T>? = null
    var right: RBTElement<T>? = null
    var parent: RBTElement<T>? = null
    var color: Color = Color.Black
}