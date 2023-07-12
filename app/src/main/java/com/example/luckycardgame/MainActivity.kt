package com.example.luckycardgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        val luckyGame = LuckyGame()
        val spaceSet = MarginSetDecoration()
        val bottomSpaceSet = MarginSetBottomDecoration()

        binding.aCardListView.addItemDecoration(spaceSet)
        binding.bCardListView.addItemDecoration(spaceSet)
        binding.cCardListView.addItemDecoration(spaceSet)
        binding.dCardListView.addItemDecoration(spaceSet)
        binding.eCardListView.addItemDecoration(spaceSet)
        binding.bottomCardListView.addItemDecoration(bottomSpaceSet)

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

            luckyGame.setParticipantsNumbers(3)

            val aAdapter = CardListAdapter(luckyGame.participantsList[0].ownCardList)
            val bAdapter = CardListAdapter(luckyGame.participantsList[1].ownCardList)
            val cAdapter = CardListAdapter(luckyGame.participantsList[2].ownCardList)
            val bottomAdapter = CardListAdapter(luckyGame.bottomCardList)

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

            luckyGame.setParticipantsNumbers(4)

            val aAdapter = CardListAdapter(luckyGame.participantsList[0].ownCardList)
            val bAdapter = CardListAdapter(luckyGame.participantsList[1].ownCardList)
            val cAdapter = CardListAdapter(luckyGame.participantsList[2].ownCardList)
            val dAdapter = CardListAdapter(luckyGame.participantsList[3].ownCardList)
            val bottomAdapter = CardListAdapter(luckyGame.bottomCardList)

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

            luckyGame.setParticipantsNumbers(5)

            val aAdapter = CardListAdapter(luckyGame.participantsList[0].ownCardList)
            val bAdapter = CardListAdapter(luckyGame.participantsList[1].ownCardList)
            val cAdapter = CardListAdapter(luckyGame.participantsList[2].ownCardList)
            val dAdapter = CardListAdapter(luckyGame.participantsList[3].ownCardList)
            val eAdapter = CardListAdapter(luckyGame.participantsList[4].ownCardList)
            val bottomAdapter = CardListAdapter(luckyGame.bottomCardList)

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