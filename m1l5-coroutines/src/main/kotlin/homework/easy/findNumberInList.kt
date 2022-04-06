package homework.easy

import kotlinx.coroutines.delay

suspend fun findNumberInList(toFind: Int, numbers: List<Int>): Int {

        //Thread.sleep(2000L)
        delay(2000L)
        return numbers.firstOrNull { it == toFind } ?: -1
}