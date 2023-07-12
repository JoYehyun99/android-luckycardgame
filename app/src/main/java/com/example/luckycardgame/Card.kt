package com.example.luckycardgame

// 제한적인 subclass 조건
// String("동물이름")을 통한 동물 구별 대신 instance를 활용
sealed class Card (open val cardNum : Int) {
    data class Dog(override val cardNum : Int, val unicode: String = "\uD83D\uDC36") : Card(cardNum)
    data class Cat(override val cardNum : Int, val unicode: String = "\uD83D\uDC31") : Card(cardNum)
    data class Cow(override val cardNum : Int, val unicode: String = "\uD83D\uDC2E") : Card(cardNum)
}