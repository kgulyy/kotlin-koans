package org.kotlinlang.koans.conventions

import org.junit.Assert
import org.junit.Test

class TestRangeTo {
    @Test
    fun testInRange() {
        doTest(MyDate(2014, 3, 22), MyDate(2014, 1, 1), MyDate(2015, 1, 1), shouldBeInRange = true)
    }

    private fun doTest(date: MyDate, first: MyDate, last: MyDate, shouldBeInRange: Boolean) {
        val message = "$date should${if (shouldBeInRange) "" else "n't"} be in range: ${DateRange(first, last)}"
        Assert.assertEquals(message, shouldBeInRange, date in first..last)
    }
}