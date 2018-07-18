package org.kotlinlang.koans.introduction

import org.junit.Assert
import org.junit.Test

class TestObjectExpressions {
    @Test
    fun testSort() {
        Assert.assertEquals("getSortedListSimple", listOf(5, 2, 1), getSortedListSimple())
    }
}