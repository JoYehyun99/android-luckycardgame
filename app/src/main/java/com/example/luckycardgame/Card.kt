package com.example.luckycardgame

sealed class Card (open val cardNum : Int){
    data class Dog(override val cardNum: Int, val unicode: Int = 0x1F436) : Card(cardNum = cardNum)
    data class Cat(override val cardNum: Int, val unicode: Int = 0x1F431) : Card(cardNum = cardNum)
    data class Cow(override val cardNum: Int, val unicode: Int = 0x1F42E) : Card(cardNum = cardNum)
}