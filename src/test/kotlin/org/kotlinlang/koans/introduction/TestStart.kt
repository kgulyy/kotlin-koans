package org.kotlinlang.koans.introduction

import org.junit.Assert
import org.junit.Test

class TestStart {
    @Test
    fun testOk() {
        Assert.assertEquals("OK", start())
    }
}