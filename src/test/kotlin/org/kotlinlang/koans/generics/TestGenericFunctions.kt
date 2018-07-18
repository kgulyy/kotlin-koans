package org.kotlinlang.koans.generics

import org.junit.Assert
import org.junit.Test
import java.util.*

class TestGenericFunctions {
    @Test
    fun testPartitionWordsAndLines() {
        val (words, lines) = listOf("a", "a b", "c", "d e").partitionTo(ArrayList(), ArrayList()) { s -> !s.contains(" ") }
        Assert.assertEquals("partitionTo", listOf("a", "c"), words)
        Assert.assertEquals("partitionTo", listOf("a b", "d e"), lines)
    }

    @Test
    fun testPartitionLettersAndOtherSymbols() {
        val (letters, other) = setOf('a', '%', 'r', '}').partitionTo(HashSet(), HashSet()) { c -> c in 'a'..'z' || c in 'A'..'Z' }
        Assert.assertEquals("partitionTo", setOf('a', 'r'), letters)
        Assert.assertEquals("partitionTo", setOf('%', '}'), other)
    }
}