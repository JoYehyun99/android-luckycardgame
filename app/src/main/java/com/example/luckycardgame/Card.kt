package com.example.luckycardgame

// 제한적인 subclass 조건
// String("동물이름")을 통한 동물 구별 대신 instance를 활용
sealed class Card {
    data class Dog(val num : Int) : Card()
    data class Cat(val num : Int) : Card()
    data class Cow(val num : Int) : Card()
}