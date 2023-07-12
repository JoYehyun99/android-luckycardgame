package com.example.luckycardgame

fun main() {
    //  0 : Dog, 1 : Cat, 2 :Cow
    val animalRange = (0..2)
    var rand: Int
    val numList = MutableList<Int>(12) { i -> i + 1 }
    numList.shuffle()
    numList.forEachIndexed { idx, num ->
        rand = animalRange.random()
        when (rand) {
            0 -> {
                val dog = Card.Dog(num)
                printItem(dog)
            }

            1 -> {
                val cat = Card.Cat(num)
                printItem(cat)
            }

            2 -> {
                val cow = Card.Cow(num)
                printItem(cow)
            }
        }
        if (idx != 11) {
            print(", ")
        }
    }
}

private fun printItem(item: Card) {
    when (item) {
        is Card.Dog -> print(String.format("%s%02d", item.unicode, item.cardNum))
        is Card.Cat -> print(String.format("%s%02d", item.unicode, item.cardNum))
        is Card.Cow -> print(String.format("%s%02d", item.unicode, item.cardNum))
    }
}