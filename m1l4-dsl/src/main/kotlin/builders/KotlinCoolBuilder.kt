package builders.new

enum class Drink {
    WATER,
    COFFEE,
    TEA
}

abstract class Meal {
    data class Breakfast(
        val eggs: Int,
        val bacon: Boolean,
        val drink: Drink,
        val title: String
    ) : Meal()
}

class BreakfastBuilder {
    var eggs = 0
    var bacon = false
    var drink = Drink.WATER
    var title = ""

    fun build() = Meal.Breakfast(eggs, bacon, drink, title)
}

fun breakfast(block: BreakfastBuilder.() -> Unit): Meal.Breakfast {
    return BreakfastBuilder().apply(block).build()
}

fun main() {

    val myBreakfast = breakfast {
        eggs = 3
        bacon = true
        drink = Drink.COFFEE
        title = "Simple"
    }

    println(myBreakfast)

}