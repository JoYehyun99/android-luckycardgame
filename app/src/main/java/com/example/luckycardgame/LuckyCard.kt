package com.example.luckycardgame

import java.util.Collections

// 유니코드 활용의 편리함을 위해서 enum class 사용
enum class Animal(val unicode: Int) {
    DOG(0x1F436),
    CAT(0x1F431),
    COW(0x1F42E)
}

fun main() {

    //  0 : Dog, 1 : Cat, 2 :Cow
    val animalRange = (0..2)
    var rand: Int
    val numList = MutableList<Int>(12) { i -> i + 1 }
    numList.shuffle() // 1 - 12 사이 중복 없는 랜덤 숫자를 위한 shuffle

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
        is Card.Dog -> print(
            String.format(
                "${String(Character.toChars(Animal.DOG.unicode))}%02d",
                item.num
            )
        )

        is Card.Cat -> print(
            String.format(
                "${String(Character.toChars(Animal.CAT.unicode))}%02d",
                item.num
            )
        )

        is Card.Cow -> print(
            String.format(
                "${String(Character.toChars(Animal.COW.unicode))}%02d",
                item.num
            )
        )
    }
}