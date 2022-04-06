package homework.easy

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.delay

//suspend fun findNumberInList(toFind: Int, numbers: List<Int>): Int {
//
//        //Thread.sleep(2000L)
//        delay(2000L)
//        return numbers.firstOrNull { it == toFind } ?: -1
//}

fun CoroutineScope.findNumberInList(toFind: Int, numbers: List<Int>): Deferred<Int> = async {

    //Thread.sleep(2000L)
    delay(2000L)
    println("async this: $this = ${this.hashCode()}")

    numbers.firstOrNull { it == toFind } ?: -1
}