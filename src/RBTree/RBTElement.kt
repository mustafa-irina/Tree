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

    override fun removeChild(newKey: T) {
        if (left?.key == newKey)
            left = null
        if (right?.key == newKey)
            right = null
    }

    fun balanceDel(){
        if (parent != null){
            if (brother()?.color == Color.Red) {
                parent?.color = Color.Red
                brother()?.color = Color.Black
                if (parent?.left == this)
                    parent?.rotateLeft()
                if (parent?.right == this)
                    parent?.rotateRight()
                parent?.balanceDel()
            }
            if (brother()?.color == Color.Black){
                if (brother()?.right?.color == Color.Black && brother()?.left?.color == Color.Black){
                    parent?.color = Color.Black
                    brother()?.color = Color.Red
                    parent?.balanceDel()
                }
                if (brother()?.right?.color == Color.Black && brother()?.left?.color == Color.Red){
                    brother()?.left?.color = Color.Black
                    brother()?.color = Color.Red
                    brother()?.rotateRight()
                    parent?.balanceDel()
                }
                if (brother()?.right?.color == Color.Red){
                    brother()?.color = parent!!.color
                    parent?.color = Color.Black
                    brother()?.right?.color == Color.Black
                    if (parent?.left == this)
                        parent?.rotateLeft()
                    if (parent?.right == this)
                        parent?.rotateRight()
                    parent?.balanceDel()
                }
            }
        }
        check = true
    }

    fun deleteRed(removeKey: T){
        var children: RBTElement<T>? = null
        if (right == null && left == null)
            parent?.removeChild(removeKey)
        else if (right == null) {
            left?.parent = parent
            if (parent == null) {
                parent = left
                return
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
                    return
                }
                if (parent!!.key > right!!.key)
                    parent?.left = right
                else
                    parent?.right = right
                return
            }
            var minRight: RBTElement<T>? = right
            while (minRight?.left != null) {
                minRight = minRight?.left
            }
            minRight?.right?.parent = minRight?.parent
            minRight?.parent?.left = minRight?.right
            children = minRight?.parent?.left
            minRight?.left = left
            minRight?.parent = parent
            minRight?.right = right
            right?.parent = minRight
            if (parent == null) {
                parent = minRight
                return
            }
            if (minRight!!.key > parent!!.key)
                parent?.right = minRight
            else
                parent?.left = minRight

        }
        if (check == false && children != null){
            children!!.balanceDel()
        } else check = true
    }

    override fun delete(removeKey: T){
        if (key > removeKey)
            left?.delete(removeKey)
        if (key < removeKey)
            right?.delete(removeKey)
        if (key == removeKey) {
            if (color == Color.Red){
                deleteRed(removeKey)
            } else if (color == Color.Black) {
                check = false
                deleteRed(removeKey)
            }
        }
    }
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

    var left: RBTElement<T>? = null
    var right: RBTElement<T>? = null
    var parent: RBTElement<T>? = null
    var color: Color = Color.Black
    var check: Boolean? = true
}