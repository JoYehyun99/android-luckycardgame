package com.example.luckycardgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.luckycardgame.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var totalParticipants: Int
        var participantCardCnt: Int
        val totalCardList: MutableList<Card> = mutableListOf()
        val totalCardListForThree: MutableList<Card> = mutableListOf()


        // 전체 카드 리스트 생성 후 shuffle
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
        //Log.d("test",totalCardList.toString())

        val spaceSet = MarginSetDecoration()
        val bottomSpaceSet = MarginSetBottomDecoration()

        binding.aCardListView.addItemDecoration(spaceSet)
        binding.bCardListView.addItemDecoration(spaceSet)
        binding.cCardListView.addItemDecoration(spaceSet)
        binding.dCardListView.addItemDecoration(spaceSet)
        binding.eCardListView.addItemDecoration(spaceSet)
        binding.bottomCardListView.addItemDecoration(bottomSpaceSet)


        // selector 사용 해보기
        binding.peopleBtns.addOnButtonCheckedListener { toggleButton, checkedId, isChecked ->
            if (isChecked) {
                when (checkedId) {
                    R.id.people3_Btn -> {
                        binding.people3Btn.icon =
                            AppCompatResources.getDrawable(applicationContext, R.drawable.checkicon)
                        binding.people4Btn.icon = null
                        binding.people5Btn.icon = null
                    }

                    R.id.people4_Btn -> {
                        binding.people3Btn.icon = null
                        binding.people4Btn.icon =
                            AppCompatResources.getDrawable(applicationContext, R.drawable.checkicon)
                        binding.people5Btn.icon = null
                    }

                    R.id.people5_Btn -> {
                        binding.people3Btn.icon = null
                        binding.people4Btn.icon = null
                        binding.people5Btn.icon =
                            AppCompatResources.getDrawable(applicationContext, R.drawable.checkicon)
                    }

                }
            }

        }

        // 3명 버튼 클릭
        binding.people3Btn.setOnClickListener {

            binding.cardD.visibility = View.INVISIBLE
            binding.cardE.visibility = View.GONE

            val totalParticipantList: MutableList<Participant> = mutableListOf()
            val bottomStageCardList: MutableList<Card> = mutableListOf()

            totalParticipants = 3
            participantCardCnt = 8

            val sharedCardList = totalCardListForThree.chunked(participantCardCnt)
            for (i in 0 until totalParticipants) {
                totalParticipantList.add(Participant(sharedCardList[i].toMutableList()))
            }
            bottomStageCardList.addAll(sharedCardList[totalParticipants])
            bottomStageCardList.addAll(sharedCardList[totalParticipants + 1])

            Log.d("participant", totalParticipantList[0].ownCardList.toString())
            Log.d("test", bottomStageCardList.toString())

            val aAdapter = CardListAdapter(totalParticipantList[0].ownCardList, false)
            val bAdapter = CardListAdapter(totalParticipantList[1].ownCardList, true)
            val cAdapter = CardListAdapter(totalParticipantList[2].ownCardList, true)
            val bottomAdapter = CardListAdapter(bottomStageCardList, true)
            //bottomAdapter.notifyDataSetChanged()

            binding.aCardListView.adapter = aAdapter
            binding.aCardListView.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)


            binding.bCardListView.adapter = bAdapter
            binding.bCardListView.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)


            binding.cCardListView.adapter = cAdapter
            binding.cCardListView.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)


            binding.bottomCardListView.adapter = bottomAdapter
            binding.bottomCardListView.layoutManager =
                GridLayoutManager(this, 2, GridLayoutManager.HORIZONTAL, false)

        }

        // 4명 버튼 클릭
        binding.people4Btn.setOnClickListener {
            binding.cardD.visibility = View.VISIBLE
            binding.cardE.visibility = View.GONE

            val totalParticipantList: MutableList<Participant> = mutableListOf()
            val bottomStageCardList: MutableList<Card> = mutableListOf()

            totalParticipants = 4
            participantCardCnt = 7

            totalCardList.shuffle()

            val sharedCardList = totalCardList.chunked(participantCardCnt)
            for (i in 0 until totalParticipants) {
                totalParticipantList.add(Participant(sharedCardList[i].toMutableList()))
            }
            bottomStageCardList.addAll(sharedCardList[totalParticipants])
            bottomStageCardList.addAll(sharedCardList[totalParticipants + 1])

            val aAdapter = CardListAdapter(totalParticipantList[0].ownCardList, false)
            val bAdapter = CardListAdapter(totalParticipantList[1].ownCardList, true)
            val cAdapter = CardListAdapter(totalParticipantList[2].ownCardList, true)
            val dAdapter = CardListAdapter(totalParticipantList[3].ownCardList, true)
            val bottomAdapter = CardListAdapter(bottomStageCardList, true)


            binding.aCardListView.adapter = aAdapter
            binding.aCardListView.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)


            binding.bCardListView.adapter = bAdapter
            binding.bCardListView.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

            binding.cCardListView.adapter = cAdapter
            binding.cCardListView.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

            binding.dCardListView.adapter = dAdapter
            binding.dCardListView.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

            binding.bottomCardListView.adapter = bottomAdapter
            binding.bottomCardListView.layoutManager =
                GridLayoutManager(this, 2, GridLayoutManager.HORIZONTAL, false)

        }

        // 5명 버튼 클릭
        binding.people5Btn.setOnClickListener {
            binding.cardD.visibility = View.VISIBLE
            binding.cardE.visibility = View.VISIBLE

            val totalParticipantList: MutableList<Participant> = mutableListOf()
            val bottomStageCardList: MutableList<Card> = mutableListOf()

            totalParticipants = 5
            participantCardCnt = 6

            totalCardList.shuffle()

            val sharedCardList = totalCardList.chunked(participantCardCnt)
            for (i in 0 until totalParticipants) {
                totalParticipantList.add(Participant(sharedCardList[i].toMutableList()))
            }
            bottomStageCardList.addAll(sharedCardList[totalParticipants])

            val aAdapter = CardListAdapter(totalParticipantList[0].ownCardList, false)
            val bAdapter = CardListAdapter(totalParticipantList[1].ownCardList, true)
            val cAdapter = CardListAdapter(totalParticipantList[2].ownCardList, true)
            val dAdapter = CardListAdapter(totalParticipantList[3].ownCardList, true)
            val eAdapter = CardListAdapter(totalParticipantList[4].ownCardList, true)
            val bottomAdapter = CardListAdapter(bottomStageCardList, true)


            binding.aCardListView.adapter = aAdapter
            binding.aCardListView.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)


            binding.bCardListView.adapter = bAdapter
            binding.bCardListView.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

            binding.cCardListView.adapter = cAdapter
            binding.cCardListView.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

            binding.dCardListView.adapter = dAdapter
            binding.dCardListView.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

            binding.eCardListView.adapter = eAdapter
            binding.eCardListView.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

            binding.bottomCardListView.adapter = bottomAdapter
            binding.bottomCardListView.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)


        }

    }

}