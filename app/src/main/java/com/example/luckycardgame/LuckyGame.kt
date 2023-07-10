package com.example.luckycardgame

class LuckyGame {

    var participantsCnt = 3
    var participantCardCnt = 8
    var participantsList: MutableList<Participant> = mutableListOf()
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
        participantsList = tmpList

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
        participantsList = tmpList
    }

    fun shareBottomCard(sharedCardList: List<List<Card>>) {

        val tmpList: MutableList<Card> = mutableListOf()
        tmpList.addAll(sharedCardList[participantsCnt])
        if (participantsCnt != 5) {
            tmpList.addAll(sharedCardList[participantsCnt + 1])
        }
        bottomCardList = tmpList

    }

    fun sortCardByNum(id: Int): MutableList<Card>? {

        var user: Participant? = participantsList.find { participant -> participant.id == id }
        if (user != null) {
            user.ownCardList.sortBy { it.cardNum }
            return user.ownCardList
        }
        return null
    }

    fun sortBottomCardByNum(): MutableList<Card> {

        bottomCardList.sortBy { it.cardNum }
        return bottomCardList

    }


    fun findWhoHaveSameThreeCard(): List<Int> {

        val result: MutableList<Int> = mutableListOf()
        participantsList.forEach { participant ->
            val tmp = participant.ownCardList.groupingBy { it.cardNum }.eachCount()
                .count { it.value == 3 }
            if (tmp > 0) {
                result.add(participant.id)
            }
        }
        return result
    }

    fun compareThreeCards(firstUserId : Int, secondUserId : Int) : Boolean{

        val firstUser = participantsList[firstUserId]
        val secondUser = participantsList[secondUserId]
        val bottomCardNum = bottomCardList.random().cardNum

        // 최소값 카드
        val firstUserMinCardNum = firstUser.ownCardList.minWith(Comparator.comparingInt{it.cardNum}).cardNum
        val secondUserMinCardNum = secondUser.ownCardList.minWith(Comparator.comparingInt{it.cardNum}).cardNum
        if(firstUserMinCardNum== secondUserMinCardNum){
            if(bottomCardNum == firstUserMinCardNum) return true
        }

        // 최대값 카드
        val firstUserMaxCardNum = firstUser.ownCardList.maxWith(Comparator.comparingInt{it.cardNum}).cardNum
        val secondUserMaxCardNum = secondUser.ownCardList.maxWith(Comparator.comparingInt{it.cardNum}).cardNum

        if(firstUserMaxCardNum== secondUserMaxCardNum){
            if(bottomCardNum == firstUserMaxCardNum) return true
        }

        return false

    }

}