package homework.easy

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis
import kotlin.time.measureTimedValue

suspend fun main() {
    val numbers = generateNumbers()
    val toFind = 10
    val toFindOther = 1000

    val time = measureTimeMillis {

        coroutineScope {

        // val ctx = newSingleThreadContext("MyOwnThread")
        // val ctx = newFixedThreadPoolContext(2,"MyOwnThread")

        val foundNumbers = listOf(
                async { findNumberInList(toFind, numbers) },
                async { findNumberInList(toFindOther, numbers) })
//                async { println("Asysnc2      : I'm working in thread ${Thread.currentThread().name}")
//                        findNumberInList(toFindOther, numbers) }
            runCatching {
                foundNumbers.forEach {
                    if (it.await() != -1) {
                        println("Your number ${it.await()} found!")
                    } else {
                        println("Not found number $toFind || $toFindOther")
                    }
                }
            }.onFailure {
                //println("Deffered still running? ${it.isActive}")
                //println("Deffered is canceled? ${it.isCancelled}")
                println("I have a problem")
            }.getOrThrow()

        }

    }

    println("Completed ib $time ms")

}