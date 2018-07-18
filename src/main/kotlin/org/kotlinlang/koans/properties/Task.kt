package org.kotlinlang.koans.properties

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

// Properties
class PropertyExample {
    var counter = 0
    var propertyWithCounter: Int? = null
        set(value) {
            field = value
            counter++
        }
}

// Lazy property
class LazyProperty(private val initializer: () -> Int) {
    private var value: Int? = null

    val lazy: Int
        get() {
            if (value == null) {
                value = initializer()
            }
            return value!!
        }
    // Delegates examples
    val lazyValue: Int by lazy(initializer)
}

// Delegates how it works
class D {
    var date: MyDate by EffectiveDate()
}

class EffectiveDate<R> : ReadWriteProperty<R, MyDate> {

    private var timeInMillis: Long? = null

    override fun getValue(thisRef: R, property: KProperty<*>): MyDate {
        return timeInMillis!!.toDate()
    }

    override fun setValue(thisRef: R, property: KProperty<*>, value: MyDate) {
        timeInMillis = value.toMillis()
    }
}
