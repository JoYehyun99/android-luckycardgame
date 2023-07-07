package com.example.luckycardgame

class LuckyGame {

    var participantsCnt = 3
    var participantCardCnt = 8
    var totalParticipantList: MutableList<Participant> = mutableListOf()
    private val totalCardList: MutableList<Card> = mutableListOf()
    private val totalCardListForThree: MutableList<Card> = mutableListOf()
    var bottomCardList: MutableList<Card> = mutableListOf()

    init {

        for (i in 1..12) {
            totalCardList.add(Card.Dog(i))
            if (i != 12) {
                totalCardListForThree.add(Card.Dog(i))
            }
        }
        for (i in 1..12) {
            totalCardList.add(Card.Cat(i))
            if (i != 12) {
                totalCardListForThree.add(Card.Cat(i))
            }
        }
        for (i in 1..12) {
            totalCardList.add(Card.Cow(i))
            if (i != 12) {
                totalCardListForThree.add(Card.Cow(i))
            }
        }

        totalCardListForThree.shuffle()
        totalCardList.shuffle()

    }

    fun setParticipantsNumbers(num: Int) {
        participantsCnt = num

        // 참여자 인원에 맞게 카드 배분
        if (num == 3) {
            shareCardToThreePP()
        } else {
            shareCardToFourOrFivePP(num)
        }

    }

    fun shareCardToThreePP() {

        participantCardCnt = 8
        val sharedCardList = totalCardListForThree.chunked(participantCardCnt)
        val tmpList: MutableList<Participant> = mutableListOf()
        for (i in 0 until participantsCnt) {
            tmpList.add(Participant(i, sharedCardList[i].toMutableList()))
        }
        totalParticipantList = tmpList

    }

    fun shareCardToFourOrFivePP(num: Int) {

        if (num == 4) {
            participantCardCnt = 7
        } else {
            participantCardCnt = 6
        }
        val sharedCardList = totalCardList.chunked(participantCardCnt)
        val tmpList: MutableList<Participant> = mutableListOf()
        for (i in 0 until participantsCnt) {
            tmpList.add(Participant(i, sharedCardList[i].toMutableList()))
        }
        totalParticipantList = tmpList
    }

    fun shareBottomCard(sharedCardList: List<List<Card>>) {

        val tmpList: MutableList<Card> = mutableListOf()
        tmpList.addAll(sharedCardList[participantsCnt])
        if (participantsCnt != 5) {
            tmpList.addAll(sharedCardList[participantsCnt + 1])
        }
        bottomCardList = tmpList

    }



}