fun sout(block: () -> Any?) {
    val result = block()
    println(result)
}

fun soutWithTimestamp(block: MyContext.() -> Any?) {
    val context = MyContext()
    val result = block(context)
    println(result)
}

class MyContext {
    fun time() = System.currentTimeMillis()
}