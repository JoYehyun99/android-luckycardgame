package com.example.luckycardgame

import kotlin.math.abs

class LuckyGame {

    companion object{
        const val GOAL_NUM = 7
    }

    var participantsCnt = 3
    var participantCardCnt = 8
    var participantsList: MutableList<Participant> = mutableListOf()
    val totalCardList: MutableList<Card> = mutableListOf()
    val totalCardListForThree: MutableList<Card> = mutableListOf()
    var bottomCardList: MutableList<Card> = mutableListOf()
    val winnerList : HashMap<Int, Int> = hashMapOf()
    var nowturn: Int = 0
    val flippedBottomCardList : MutableList<Card> = mutableListOf()

    init {
        for (i in 1..12) {
            totalCardList.add(Card.Dog(i))
            totalCardList.add(Card.Cow(i))
            totalCardList.add(Card.Cow(i))
            if(i != 12){
                totalCardListForThree.add(Card.Dog(i))
                totalCardListForThree.add(Card.Cat(i))
                totalCardListForThree.add(Card.Cow(i))
            }
        }
        totalCardListForThree.shuffle()
        //totalCardList.shuffle()
    }

    fun setParticipantsNumbers(num: Int) {
        participantsCnt = num
        if (num == 3) {
            shareCardToThreePP()
        } else {
            shareCardToFourOrFivePP(num)
        }
        // 각각 오름차순 정렬로 초기화
        for (i in 0 until participantsCnt) {
            sortCardByNum(i)
        }
        //sortBottomCardByNum()
    }

    fun shareCardToThreePP() {
        participantCardCnt = 8
        val sharedCardList = totalCardListForThree.chunked(participantCardCnt)
        val tmpList: MutableList<Participant> = mutableListOf()
        for (i in 0 until participantsCnt) {
            tmpList.add(Participant(i, sharedCardList[i].toMutableList()))
        }
        participantsList = tmpList
        shareBottomCard(sharedCardList)
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
        shareBottomCard(sharedCardList)
    }

    fun shareBottomCard(sharedCardList: List<List<Card>>) {

        val tmpList: MutableList<Card> = mutableListOf()
        for (card in sharedCardList[participantsCnt]){
            card.bottom = true
            tmpList.add(card)
        }
        if (participantsCnt != 5) {
            for (card in sharedCardList[participantsCnt+1]){
                card.bottom = true
                tmpList.add(card)
            }
        }
        bottomCardList = tmpList
    }

    fun sortCardByNum(id: Int): MutableList<Card>? {
        val user = participantsList.find { participant -> participant.id == id }
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

    fun findWhoHasSameThreeCard(): Map<Int, Participant> {
        val result: MutableMap<Int,Participant> = mutableMapOf()

        participantsList.forEach { participant ->
            val flippedCard = participant.ownCardList.filter { card -> !card.flipped }
            val cardCnt = flippedCard.groupingBy { it.cardNum }.eachCount()
            val sameThreeCards = cardCnt.filter { it.value == 3 }.keys.toList()

            if (sameThreeCards.isNotEmpty()) {
                sameThreeCards.forEach { cardNum ->
                    result[cardNum] = participant
                }
            }
        }
        return result
    }

    fun compareThreeCards(
        firstUserId: Int,
        secondUserId: Int,
        bottomCardNum: Int = bottomCardList.random().cardNum
    ): Int {
        val firstUser = participantsList[firstUserId]
        val secondUser = participantsList[secondUserId]

        val firstUserMinCardNum =
            firstUser.ownCardList.minWith(Comparator.comparingInt { it.cardNum }).cardNum
        val secondUserMinCardNum =
            secondUser.ownCardList.minWith(Comparator.comparingInt { it.cardNum }).cardNum
        if (firstUserMinCardNum == secondUserMinCardNum) {
            if (bottomCardNum == firstUserMinCardNum) return bottomCardNum
        }

        val firstUserMaxCardNum =
            firstUser.ownCardList.maxWith(Comparator.comparingInt { it.cardNum }).cardNum
        val secondUserMaxCardNum =
            secondUser.ownCardList.maxWith(Comparator.comparingInt { it.cardNum }).cardNum

        if (firstUserMaxCardNum == secondUserMaxCardNum) {
            if (bottomCardNum == firstUserMaxCardNum) return bottomCardNum
        }
        return -1
    }

    fun switchTurn(): Int{
        val before = nowturn
        nowturn += 1
        if(nowturn == participantsCnt){
            nowturn = 0
        }
        return before
    }

    fun flipCard(userId: Int, pos: Int) : Boolean {

        if(userId == -1){   //  바닥 카드일 경우
            val bottomCard = bottomCardList[pos]
            return if(bottomCard.flipped){
                bottomCard.flipped = false
                participantsList[nowturn].ownCardList.add(bottomCard)
                flippedBottomCardList.add(bottomCard)
                true
            } else{ // 이미 뒤집혀 있다면,
                false
            }
        }
        else{
            val userCardList = participantsList[userId].ownCardList
            if(!userCardList[pos].flipped) return false
            return if (pos == 0 || pos == (userCardList.size - 1)) {
                userCardList[pos].flipped = false
                true
            } else if (!userCardList[pos - 1].flipped || !userCardList[pos + 1].flipped){
                userCardList[pos].flipped = false
                true
            } else{
                false
            }
        }
    }

    fun checkAllCardsFlipped(): Boolean{
        return participantsList.all { participant ->
            participant.ownCardList.all { card -> !card.flipped }
        }
    }

    fun combination(
        answer: MutableList<List<Int>>,
        elements: List<Int>,
        count: Int,
        start: Int,
        used : Array<Boolean>
    ){
        if(count==0){
            answer.addAll(listOf(elements.filterIndexed{idx, t -> used[idx]}))
        } else{
            for(i in start until elements.size){
                used[i] = true
                combination(answer,elements,count-1,i+1,used)
                used[i] = false
            }
        }
    }

    fun checkCombinationForSumOrDiffSeven(combination : List<List<Int>>): List<Int>?{
        for (pair in combination){
            val sum = pair.sum()
            val diff = abs(pair[0] - pair[1])
            if(sum == 7 || diff == 7){
                return pair
            }
        }
        return null
    }

    fun isGameEnd(): Boolean{

        val sameCards = findWhoHasSameThreeCard()
        if(sameCards.isNotEmpty()){
            val numList = sameCards.keys.toList()

            if(numList.contains(GOAL_NUM)) {
                // 숫자 7 카드를 가지고 있는 이용자가 있는 경우
                winnerList[GOAL_NUM] = sameCards.getValue(GOAL_NUM).id
                return true
            } else{
                // 7외의 숫자들만 있는 경우 -> 합/차 조합 테스트 해보기
                for (i in 2..numList.size){
                    val combinationList = mutableListOf<List<Int>>()
                    combination(combinationList,numList, i, 0, Array(numList.size) { false })

                    val result = checkCombinationForSumOrDiffSeven(combinationList)
                    if(result != null){
                        // 승자 추가
                        result.forEach{ num ->
                            winnerList[num] = sameCards.getValue(num).id
                        }
                        return true
                    }
                }
                // 합/차 7인 조합은 없지만, 동일한 세 개 숫자 카드가 있는 경우
                // 동일한 세 개 숫자 카드에 속한 바닥 카드는 유지
                // 속하지 않은 카드는 원상 복구
                bottomCardList.forEach { card ->
                    if(!numList.contains(card.cardNum)) card.flipped = true
                }
            }
        } else{ // 모든 바닥 카드 원상 복구
            for(participant in participantsList){
                participant.ownCardList.removeIf { card -> flippedBottomCardList.contains(card) }
            }
            bottomCardList.forEach{card ->
                card.flipped = true
            }
        }
        flippedBottomCardList.clear()
        return checkAllCardsFlipped()
    }



}