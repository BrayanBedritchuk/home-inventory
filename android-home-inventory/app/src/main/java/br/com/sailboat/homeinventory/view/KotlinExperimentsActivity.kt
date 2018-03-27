package br.com.sailboat.homeinventory.view

import android.support.v7.app.AppCompatActivity
import android.util.Log
import java.io.File

class KotlinExperimentsActivity : AppCompatActivity() {

    // Start Basics Section

    val a = 10 // IMUTABLE
    var b = 10 // MUTABLE

    val isEmpty: Boolean get() = 10 == 0


    val THIS_IS_A_CONSTANT = 10

    // why use const?

    // class names: captalize the first two letters when is a acronym... captalize the fist one when it has more

    fun sum1(a: Int = 0, b: Int = 0): Int {
        return a + b
    }

    fun sum2(a: Int, b: Int): Int = a + b

    fun printSum(a: Int, b: Int, any: Any) {
        Log.v("TAG", "sum of $a and $b is $ {a + b}")

        if (any is String) {
            Log.v("TAG", "any is a String!!!")
        }

        if (any !is String) {
            Log.v("TAG", "any isn't a String :(")
        }

    }

    fun forLoops() {
        val items = listOf("apple", "banana", "kiwi")

        val lastIndex = items.lastIndex

        for (item in items) {
            print(item)
        }

        for (index in items.indices) {
            print("item at $index is ${items[index]}")
        }

        for (x in 1..5) { // one to five
            print("x: $x")
        }

        for (x in 1 until 5) { // one to four
            print("x: $x")
        }

        var mapTest = mapOf("Brayan" to 1, "Else" to 2)

        for ((k, v) in mapTest) {
            println("Key: $k, Value: $v")
        }


    }

    fun whenTest(any: Any) {
        when (any) {
            1 -> "One"
            "Hello" -> "Greeting"
            is Long -> "Long"
            else -> "Unknown"
        }

        val items = listOf("apple", "banana", "kiwi")

        when {
            "orange" in items -> println("juicy")
        }
    }

    fun rangeTest() {
        val x = 10
        val y = 9

        if (x in 1..y + 1) {
            print("$x fits in range")
        }

        val string = String() // NO NEW KEYWORD
    }

    fun filterListWithLambdaTest() {
        val items = listOf("apple", "banana", "kiwi")

        items.filter { it.startsWith("a") }
            .sortedBy { it }
            .map { it.toUpperCase() }
            .forEach { println(it) }

        val integers = listOf(1, 2, 3, 4)

        var positives = integers.filter { x -> x > 0 }

        // OR

        positives = integers.filter { it > 0 }

    }

    object SingletonResource {

    }

    fun ifNotNullTest() {
        val file = File("Test").listFiles()

        // if not null
        println(file?.size)

        // if not null with else
        println(file?.size ?: "empty")

        var string = null

        // if null, throw exception
        string ?: throw IllegalStateException("String is null")


        val items = listOf("apple", "banana", "kiwi")

        val item = items.firstOrNull() ?: ""

        item?.let {
            // execute this if null
        }

    }

    fun tryCatchTest() {
        val result = try {
            10
        } catch (e: Exception) {
            throw IllegalStateException(e)
        }
    }


    fun getResult(): Int {
        val value = if (10 < 100) {
            10
        } else {
            20
        }

        return value
    }

    class Test {
        fun fun1() {

        }

        fun fun2() {

        }
    }

    fun callMultipleMethodsWithWith() {
        val test = Test()

        with(test) {

            fun1()
            fun2()
        }
    }

    fun longMethodName(
        argument: Int = 0,
        argument2: Long = 0
    ): Int {
        return 10
    }

    fun ifTest() {
        if (thisIsALongMethod() &&
            thisIsHowYouShouldHandle()
        ) {
            // body
        }
    }

    private fun thisIsHowYouShouldHandle(): Boolean {
        return true
    }

    private fun thisIsALongMethod(): Boolean {
        return true
    }


    // ALWAYS AT THE BOTTOM
    companion object {

    }


}