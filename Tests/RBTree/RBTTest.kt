package RBTree

import RBT.RBT
import RBT.RBTElement
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class RBTTest{
    val testRBTree = RBT<Int>()

    @Test
    fun colorRoot(){
        testRBTree.add(1)
        assertEquals(testRBTree.root?.color, RBTElement.Color.Black)
        for (i in 1..100) {
            testRBTree.add(i)
            assertEquals(testRBTree.root?.color, RBTElement.Color.Black)
        }
    }

    @Test
    fun deleteExistent() {
        for (i in 1..100)
            testRBTree.add(i)
        testRBTree.delete(14)
        assertFalse(testRBTree.find(14))
    }

    @Test
    fun deleteNonexistent() {
        for (i in 1..100)
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
    fun findNonexistent() {
        assertFalse(testRBTree.find(19))
    }

    @Test
    fun iteratorTest() {
        var k: Int = 0
        for (i in 1..100)
            testRBTree.add(i)
        for (element in testRBTree) {
            assertTrue(element.key in 1..100)
            k++
        }
        assertEquals(k, 100)
    }
}