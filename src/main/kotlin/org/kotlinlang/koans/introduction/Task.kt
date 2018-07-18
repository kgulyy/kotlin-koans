package org.kotlinlang.koans.introduction

import java.util.*

// Hello, world!
fun start(): String = "OK"

// Java to Kotlin conversion
fun toJSON(collection: Collection<Int>): String {
    val sb = StringBuilder()
    sb.append("[")
    val iterator = collection.iterator()
    while (iterator.hasNext()) {
        val element = iterator.next()
        sb.append(element)
        if (iterator.hasNext()) {
            sb.append(", ")
        }
    }
    sb.append("]")
    return sb.toString()
}

// Named arguments
fun joinOptions(options: Collection<String>) = options.joinToString(prefix = "[", postfix = "]")

// Default arguments
fun foo(name: String, number: Int = 42, toUpperCase: Boolean = false) =
        (if (toUpperCase) name.toUpperCase() else name) + number

fun useFoo() = listOf(
        foo("a"),
        foo("b", number = 1),
        foo("c", toUpperCase = true),
        foo(name = "d", number = 2, toUpperCase = true)
)

// Lambdas
fun containsEven(collection: Collection<Int>): Boolean = collection.any { i -> i % 2 == 0 }

// Strings
const val month = "(JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC)"

fun getPattern(): String = """\d{2} $month \d{4}"""

// Data classes
data class Person(val name: String, val age: Int)

fun getPeople(): List<Person> {
    return listOf(Person("Alice", 29), Person("Bob", 31))
}

// Nullable types
class Client(val personalInfo: PersonalInfo?)

class PersonalInfo(val email: String?)
interface Mailer {
    fun sendMessage(email: String, message: String)
}

fun sendMessageToClient(
        client: Client?, message: String?, mailer: Mailer
) {
    val email = client?.personalInfo?.email
    if (email != null && message != null)
        mailer.sendMessage(email, message)
}

// Smart casts
interface Expr

class Num(val value: Int) : Expr
class Sum(val left: Expr, val right: Expr) : Expr

fun eval(expr: Expr): Int =
        when (expr) {
            is Num -> expr.value
            is Sum -> eval(expr.left) + eval(expr.right)
            else -> throw IllegalArgumentException("Unknown expression")
        }


// Extension functions
data class RationalNumber(val numerator: Int, val denominator: Int)

fun Int.r(): RationalNumber = RationalNumber(this, 1)
fun Pair<Int, Int>.r(): RationalNumber = RationalNumber(this.first, this.second)

// Object expressions
@Suppress("ObjectLiteralToLambda")
fun getSortedListSimple(): List<Int> {
    val arrayList = arrayListOf(1, 5, 2)
    Collections.sort(arrayList, object : Comparator<Int> {
        override fun compare(x: Int, y: Int) = y - x
    })
    return arrayList
}

// SAM conversions
@Suppress("JavaCollectionsStaticMethod")
fun getSortedListSAM(): List<Int> {
    val arrayList = arrayListOf(1, 5, 2)
    Collections.sort(arrayList) { x, y -> y - x }
    return arrayList
}

// Extension functions on collections
fun getSortedListStandard(): List<Int> {
    return arrayListOf(1, 5, 2).sortedDescending()
}
