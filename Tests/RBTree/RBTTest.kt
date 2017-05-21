package RBTree

import RBT.RBT
import RBT.RBTElement
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.util.*

internal class RBTTest{
    val testRBTree = RBT<Int>()

    @Test
    fun colorRoot(){
        testRBTree.add(1)
        assertEquals(testRBTree.root?.color, RBTElement.Color.Black)
        for (i in 100..1) {
           // val random = Random()
            testRBTree.add(i)
            assertEquals(testRBTree.root?.color, RBTElement.Color.Black)
        }
    }

    @Test
    fun deletion() {
        for (i in 100..1)
            testRBTree.add(i)
        testRBTree.delete(14)
        assertFalse(testRBTree.find(14))
    }

    @Test
    fun deletioff() {
        for (i in 100..1)
            testRBTree.add(i)
        testRBTree.delete(105)
        assertFalse(testRBTree.find(105))
    }

    @Test
    fun rootDeletion() {
        testRBTree.add(14)
        testRBTree.delete(14)
        assertNull(testRBTree.root)
    }

    @Test
    fun findcho() {
        assertFalse(testRBTree.find(19))
    }

    @Test
    fun iteratorTest() {}
}