package org.kotlinlang.koans.introduction

import org.junit.Assert
import org.junit.Test

class TestFunctions {
    @Test
    fun collection() {
        Assert.assertEquals("toJSON", "[1, 2, 3, 42, 555]", toJSON(listOf(1, 2, 3, 42, 555)))
    }
}