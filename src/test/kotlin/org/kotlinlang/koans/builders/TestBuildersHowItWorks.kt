package org.kotlinlang.koans.builders

import org.junit.Assert
import org.junit.Test
import org.kotlinlang.koans.builders.Answer.B
import org.kotlinlang.koans.builders.Answer.C

class TestBuildersHowItWorks {
    @Test
    fun testBuildersQuiz() {
        if (answers.values.toSet() == setOf(null)) {
            Assert.fail("Please specify your answers!")
        }
        val correctAnswers = mapOf(22 - 20 to B, 1 + 3 to C, 11 - 8 to B, 79 - 78 to C)
        if (correctAnswers != answers) {
            val incorrect = (1..4).filter { answers[it] != correctAnswers[it] }
            Assert.fail("Your answers are incorrect! $incorrect")
        }
    }
}