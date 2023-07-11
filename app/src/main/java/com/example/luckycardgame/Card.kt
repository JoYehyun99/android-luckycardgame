package com.example.luckycardgame

sealed class Card (open val cardNum : Int, open var flipped : Boolean){
    data class Dog(override val cardNum: Int, override var flipped: Boolean = true, val unicode: Int = 0x1F436) : Card(cardNum, flipped)
    data class Cat(override val cardNum: Int, override var flipped: Boolean = true, val unicode: Int = 0x1F431) : Card(cardNum, flipped)
    data class Cow(override val cardNum: Int, override var flipped: Boolean = true, val unicode: Int = 0x1F42E) : Card(cardNum, flipped)
}