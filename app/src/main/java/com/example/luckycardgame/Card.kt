package com.example.luckycardgame

sealed class Card(open val cardNum: Int, open var flipped: Boolean, open var bottom: Boolean) {
    data class Dog(
        override val cardNum: Int,
        override var flipped: Boolean = true,
        val unicode: String = "\uD83D\uDC36",
        override var bottom: Boolean = false
    ) : Card(cardNum, flipped, bottom)

    data class Cat(
        override val cardNum: Int,
        override var flipped: Boolean = true,
        val unicode: String = "\uD83D\uDC31",
        override var bottom: Boolean = false
    ) : Card(cardNum, flipped, bottom)

    data class Cow(
        override val cardNum: Int,
        override var flipped: Boolean = true,
        val unicode: String = "\uD83D\uDC2E",
        override var bottom: Boolean = false
    ) : Card(cardNum, flipped, bottom)
}