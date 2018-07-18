package org.kotlinlang.koans.introduction

import org.junit.Assert
import org.junit.Test

class TestNamedArguments {

    @Test
    fun testJoinToString() {
        Assert.assertEquals("joinOptions", "[yes, no, may be]", joinOptions(listOf("yes", "no", "may be")))
    }

}