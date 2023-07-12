package com.example.luckycardgame

sealed class Card (open val cardNum : Int, open var flipped : Boolean){
    data class Dog(override val cardNum: Int, override var flipped: Boolean = true, val unicode: String = "\uD83D\uDC36") : Card(cardNum, flipped)
    data class Cat(override val cardNum: Int, override var flipped: Boolean = true, val unicode: String = "\uD83D\uDC31") : Card(cardNum, flipped)
    data class Cow(override val cardNum: Int, override var flipped: Boolean = true, val unicode: String = "\uD83D\uDC2E") : Card(cardNum, flipped)
}