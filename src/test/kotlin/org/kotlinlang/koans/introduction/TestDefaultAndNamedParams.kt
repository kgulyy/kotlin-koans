package org.kotlinlang.koans.introduction

import org.junit.Assert
import org.junit.Test

class TestDefaultAndNamedParams {

    @Test
    fun testDefaultAndNamedParams() {
        Assert.assertEquals(listOf("a42", "b1", "C42", "D2"), useFoo())
    }
}