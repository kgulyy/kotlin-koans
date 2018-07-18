package org.kotlinlang.koans.introduction

import org.junit.Assert
import org.junit.Test

class TestExtensionsOnCollections {
    @Test
    fun testSort() {
        Assert.assertEquals(listOf(5, 2, 1), getSortedListStandard())
    }
}