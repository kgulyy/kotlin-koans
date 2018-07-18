package org.kotlinlang.koans.collections

import org.junit.Assert
import org.junit.Test

//products
val idea = Product("IntelliJ IDEA Ultimate", 199.0)
val reSharper = Product("ReSharper", 149.0)
val dotTrace = Product("DotTrace", 159.0)
val dotMemory = Product("DotTrace", 129.0)
val phpStorm = Product("PhpStorm", 99.0)
val rubyMine = Product("RubyMine", 99.0)
val webStorm = Product("WebStorm", 49.0)
val youTrack = Product("YouTrack", 500.0)

//customers
const val lucas = "Lucas"
const val cooper = "Cooper"
const val nathan = "Nathan"
const val reka = "Reka"
const val bajram = "Bajram"
const val asuka = "Asuka"
const val riku = "Riku"

//cities
val Canberra = City("Canberra")
val Vancouver = City("Vancouver")
val Budapest = City("Budapest")
val Ankara = City("Ankara")
val Tokyo = City("Tokyo")

fun customer(name: String, city: City, vararg orders: Order) = Customer(name, city, orders.toList())
fun order(vararg products: Product, isDelivered: Boolean = true) = Order(products.toList(), isDelivered)
fun shop(name: String, vararg customers: Customer) = Shop(name, customers.toList())

val shop = shop("jb test shop",
        customer(lucas, Canberra,
                order(reSharper),
                order(reSharper, dotMemory, dotTrace)
        ),
        customer(cooper, Canberra),
        customer(nathan, Vancouver,
                order(rubyMine, webStorm)
        ),
        customer(reka, Budapest,
                order(idea, isDelivered = false),
                order(idea, isDelivered = false),
                order(idea)
        ),
        customer(bajram, Ankara,
                order(reSharper)
        ),
        customer(asuka, Tokyo,
                order(idea)
        ),
        customer(riku, Tokyo,
                order(phpStorm, phpStorm),
                order(phpStorm)
        )

)

val customers: Map<String, Customer> = shop.customers.fold(hashMapOf()) { map, customer ->
    map[customer.name] = customer
    map
}

val orderedProducts = setOf(idea, reSharper, dotTrace, dotMemory, rubyMine, webStorm, phpStorm)

val sortedCustomers = listOf(cooper, nathan, bajram, asuka, lucas, riku, reka).map { customers[it] }

val groupedByCities = mapOf(
        Canberra to listOf(lucas, cooper),
        Vancouver to listOf(nathan),
        Budapest to listOf(reka),
        Ankara to listOf(bajram),
        Tokyo to listOf(asuka, riku)
).mapValues { it.value.map { name -> customers[name] } }

class TestShop {
    @Test
    fun testSetOfCustomers() {
        Assert.assertEquals("getSetOfCustomers",
                customers.values.toSet(), shop.getSetOfCustomers())
    }

    @Test
    fun testCitiesCustomersAreFrom() {
        Assert.assertEquals("getCitiesCustomersAreFrom",
                setOf(Canberra, Vancouver, Budapest, Ankara, Tokyo), shop.getCitiesCustomersAreFrom())
    }

    @Test
    fun testCustomersFromCity() {
        Assert.assertEquals("getCustomersFrom",
                listOf(customers[lucas], customers[cooper]), shop.getCustomersFrom(Canberra))
    }

    @Test
    fun testAllCustomersAreFromCity() {
        Assert.assertFalse("checkAllCustomersAreFrom", shop.checkAllCustomersAreFrom(Canberra))
    }

    @Test
    fun testAnyCustomerIsFromCity() {
        Assert.assertTrue("hasCustomerFrom", shop.hasCustomerFrom(Canberra))
    }

    @Test
    fun testCountCustomersFromCity() {
        Assert.assertEquals("countCustomersFrom", 2, shop.countCustomersFrom(Canberra))
    }

    @Test
    fun testAnyCustomerFromCity() {
        Assert.assertEquals("findAnyCustomerFrom", customers[lucas], shop.findAnyCustomerFrom(Canberra))
        Assert.assertEquals("findAnyCustomerFrom", null, shop.findAnyCustomerFrom(City("Chicago")))
    }

    @Test
    fun testGetOrderedProductsSet() {
        Assert.assertEquals("getOrderedProducts",
                setOf(idea), customers[reka]!!.orderedProducts)
    }

    @Test
    fun testGetAllOrderedProducts() {
        Assert.assertEquals("getAllOrderedProducts",
                orderedProducts, shop.allOrderedProducts)
    }

    @Test
    fun testCustomerWithMaximumNumberOfOrders() {
        Assert.assertEquals("getCustomerWithMaximumNumberOfOrders",
                customers[reka], shop.getCustomerWithMaximumNumberOfOrders())
    }

    @Test
    fun testTheMostExpensiveOrderedProduct() {
        Assert.assertEquals("getMostExpensiveOrderedProduct",
                rubyMine, customers[nathan]!!.getMostExpensiveOrderedProduct())
    }

    @Test
    fun testGetCustomersSortedByNumberOfOrders() {
        Assert.assertEquals("getCustomersSortedByNumberOfOrders",
                sortedCustomers, shop.getCustomersSortedByNumberOfOrders())
    }

    @Test
    fun testGetTotalOrderPrice() {
        Assert.assertEquals("getTotalOrderPrice",
                148.0, customers[nathan]!!.getTotalOrderPrice(), 0.001)
    }

    @Test
    fun testTotalPriceForRepeatedProducts() {
        Assert.assertEquals("getTotalOrderPrice",
                586.0, customers[lucas]!!.getTotalOrderPrice(), 0.001)
    }

    @Test
    fun testGroupCustomersByCity() {
        Assert.assertEquals("groupCustomersByCity",
                groupedByCities, shop.groupCustomersByCity())
    }

    @Test
    fun testGetCustomersWhoHaveMoreUndeliveredOrdersThanDelivered() {
        Assert.assertEquals("getCustomerWithMaximumNumberOfOrders",
                setOf(customers[reka]), shop.getCustomersWithMoreUndeliveredOrdersThanDelivered())
    }

    @Test
    fun testGetProductsOrderedByAllCustomers() {
        val testShop = shop("test shop for 'fold'",
                customer(lucas, Canberra,
                        order(idea),
                        order(webStorm)
                ),
                customer(reka, Budapest,
                        order(idea),
                        order(youTrack)
                )
        )
        Assert.assertEquals("getSetOfProductsOrderedByEveryCustomer",
                setOf(idea), testShop.getSetOfProductsOrderedByEveryCustomer())

    }

    @Test
    fun testMostExpensiveDeliveredProduct() {
        val testShop = shop("test shop for 'most expensive delivered product'",
                customer(lucas, Canberra,
                        order(isDelivered = false, products = *arrayOf(idea)),
                        order(reSharper)
                )
        )
        Assert.assertEquals("getMostExpensiveDeliveredProduct", reSharper, testShop.customers[0].getMostExpensiveDeliveredProduct())
    }

    @Test
    fun testNumberOfTimesEachProductWasOrdered() {
        Assert.assertEquals(4, shop.getNumberOfTimesProductWasOrdered(idea))
    }

    @Test
    fun testNumberOfTimesEachProductWasOrderedForRepeatedProduct() {
        Assert.assertEquals("A customer may order a product for several times",
                3, shop.getNumberOfTimesProductWasOrdered(reSharper))
    }

    @Test
    fun testNumberOfTimesEachProductWasOrderedForRepeatedInOrderProduct() {
        Assert.assertEquals("An order may contain a particular product more than once",
                3, shop.getNumberOfTimesProductWasOrdered(phpStorm))
    }

    @Test
    fun testCollectionOfOneElement() {
        doTest(listOf("a"), listOf("a"))
    }

    @Test
    fun testSimpleCollection() {
        doTest(listOf("a", "c"), listOf("a", "bb", "c"))
    }

    @Test
    fun testCollectionWithEmptyStrings() {
        doTest(listOf("", "", "", ""), listOf("", "", "", "", "a", "bb", "ccc", "dddd"))
    }

    @Test
    fun testCollectionWithTwoGroupsOfMaximalSize() {
        doTest(listOf("a", "c"), listOf("a", "bb", "c", "dd"))
    }

    private fun doTest(expected: Collection<String>?, argument: Collection<String>) {
        Assert.assertEquals("The function 'doSomethingStrangeWithCollection' should do at least something with a collection",
                expected, doSomethingStrangeWithCollection(argument))
    }
}