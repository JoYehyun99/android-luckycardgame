package com.example.luckycardgame

import org.junit.Test
import org.junit.Assert.*
import org.junit.Before

class LuckyCardTest {

    private lateinit var luckyGame: LuckyGame
    @Before
    fun setupLuckyGame() {
        luckyGame = LuckyGame()
        luckyGame.setParticipantsNumbers(3) // 게임 인원을 3명으로 임의로 설정
    }
    @Test
    fun initialSetting_countAnimalCards_eachHas12Cards() {

        val groupByAnimal = luckyGame.totalCardList.groupBy { card ->
            when (card) {
                is Card.Dog -> "Dog"
                is Card.Cat -> "Cat"
                is Card.Cow -> "Cow"
            }
        }.mapValues { (_, animalCards) ->
            animalCards.size
        }
        val answer = arrayOf(12, 12, 12)

        assertArrayEquals(answer, groupByAnimal.values.toTypedArray())
    }
    @Test
    fun initialSetting_countAnimalCardsFor3People_NoNumber12() {

        val cardSet = luckyGame.totalCardListForThree
        assertTrue(cardSet.all { it.cardNum < 12 })

    }
    @Test
    fun shareCard_toParticipants_properly() {

        luckyGame.setParticipantsNumbers(3)
        assertEquals(8, luckyGame.participantsList[0].ownCardList.size)

        luckyGame.setParticipantsNumbers(4)
        assertEquals(7, luckyGame.participantsList[0].ownCardList.size)

        luckyGame.setParticipantsNumbers(5)
        assertEquals(6, luckyGame.participantsList[0].ownCardList.size)

    }
    @Test
    fun shareCard_toBottom_properly() {

        luckyGame.setParticipantsNumbers(3)
        assertEquals(9, luckyGame.bottomCardList.size)

        luckyGame.setParticipantsNumbers(4)
        assertEquals(8, luckyGame.bottomCardList.size)

        luckyGame.setParticipantsNumbers(5)
        assertEquals(6, luckyGame.bottomCardList.size)

    }
    @Test
    fun sortCard_byParticipant_inAscendingOrder() {

        val userId = 1
        luckyGame.sortCardByNum(userId)
        val userCardList = luckyGame.participantsList[userId].ownCardList
        val testCardList = userCardList.zipWithNext { card1, card2 ->
            card1.cardNum <= card2.cardNum
        }.all { it }
        assertTrue(testCardList)

    }
    @Test
    fun sortCard_bottom_inAscendingOrder() {

        luckyGame.sortBottomCardByNum()
        val testCardList = luckyGame.bottomCardList.zipWithNext { card1, card2 ->
            card1.cardNum <= card2.cardNum
        }.all { it }
        assertTrue(testCardList)
    }
    @Test
    fun findParticipant_WhoHasSameThreeCard_whoAndWhatNumbers() {

        val result = luckyGame.findWhoHasSameThreeCard()
        result.forEach { (participant, cardNums) ->
            val tmp = participant.ownCardList.filter { card -> cardNums.contains(card.cardNum) }
            assertTrue(tmp.size % 3 == 0)
        }

    }
    @Test
    fun compareThreeCards_twoParticipantsAndBottom_isTrue() {

        val user1 = 1
        val user2 = 2
        val bottomCardNum = luckyGame.bottomCardList.random().cardNum

        val result = luckyGame.compareThreeCards(user1, user2, bottomCardNum)

        if (result != -1) {
            assertTrue(luckyGame.participantsList[user1].ownCardList.any { card -> card.cardNum == result })
            assertTrue(luckyGame.participantsList[user2].ownCardList.any { card -> card.cardNum == result })
            assertTrue(luckyGame.bottomCardList.any { card -> card.cardNum == result })
        } else {
            assertFalse(luckyGame.participantsList[user1].ownCardList.all { card -> card.cardNum == bottomCardNum })
            assertFalse(luckyGame.participantsList[user2].ownCardList.all { card -> card.cardNum == bottomCardNum })
        }
    }

}