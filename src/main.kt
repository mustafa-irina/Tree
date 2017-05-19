import BST.BST
import BTree.BT
import RBT.RBT
import Tree.Tree

fun main(args: Array<String>) {
    var tree: Tree<Int> = BT()
    tree?.add(10)
    tree?.add(5)
    tree?.add(4)
    tree?.add(3)
    tree?.add(7)
    tree?.add(17)
    tree?.add(8)
    tree?.printTree()
    println()
    println(tree?.find(7))
    println(tree?.find(100))
//    tree?.delete(10)
//    tree?.printTree()
//    println()
//    println(tree?.find(10))
//    tree?.delete(5)
//    tree?.printTree()
//    println(tree?.find(5))
    /*tree?.delete(12)
    tree?.printTree()
    println()
    tree?.delete(8)
    tree?.printTree()
    println()
    println(tree?.find(8))
    tree?.delete(10)
    tree?.printTree()
    println()
    println(tree?.find(10))
    tree?.delete(17)
    tree?.printTree()*/
}