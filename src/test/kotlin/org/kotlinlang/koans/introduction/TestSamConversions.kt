package org.kotlinlang.koans.introduction

import org.junit.Assert
import org.junit.Test

class TestSamConversions {
    @Test
    fun testSort() {
        Assert.assertEquals("getSortedListSimple", listOf(5, 2, 1), getSortedListSAM())
    }
}