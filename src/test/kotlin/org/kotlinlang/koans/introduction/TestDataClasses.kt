package org.kotlinlang.koans.introduction

import org.junit.Assert
import org.junit.Test

class TestDataClasses {
    @Test
    fun testListOfPeople() {
        Assert.assertEquals("[Person(name=Alice, age=29), Person(name=Bob, age=31)]", getPeople().toString())
    }
}