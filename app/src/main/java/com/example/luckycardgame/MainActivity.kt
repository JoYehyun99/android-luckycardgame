package com.example.luckycardgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.luckycardgame.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), CardListAdapter.OnCardClickListener {

    private val luckyGame = LuckyGame()
    private val turn = mutableMapOf(0 to 3, 1 to 3, 2 to 3,3 to 3, 4 to 3)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val spaceSet = MarginSetDecoration()
        val bottomSpaceSet = MarginSetBottomDecoration()
        val views = listOf(binding.aCardListView, binding.bCardListView,binding.cCardListView,binding.dCardListView,binding.eCardListView)
        for(view in views){
            view.addItemDecoration((spaceSet))
        }
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

            val adapters : MutableList<CardListAdapter> = mutableListOf()
            for (i in 0 until luckyGame.participantsCnt){
                adapters.add(CardListAdapter(luckyGame.participantsList[i].ownCardList,i, this@MainActivity))
            }
            val bottomAdapter = CardListAdapter(luckyGame.bottomCardList,-1,this@MainActivity)

            for((view,adapter) in views.zip(adapters)){
                view.adapter = adapter
                view.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            }
            binding.bottomCardListView.adapter = bottomAdapter
            binding.bottomCardListView.layoutManager =
                GridLayoutManager(this, 2, GridLayoutManager.HORIZONTAL, false)

        }

        // 4명 버튼 클릭
        binding.people4Btn.setOnClickListener {
            binding.cardD.visibility = View.VISIBLE
            binding.cardE.visibility = View.GONE

            luckyGame.setParticipantsNumbers(4)

            val adapters : MutableList<CardListAdapter> = mutableListOf()
            for (i in 0 until luckyGame.participantsCnt){
                adapters.add(CardListAdapter(luckyGame.participantsList[i].ownCardList,i, this@MainActivity))
            }
            val bottomAdapter = CardListAdapter(luckyGame.bottomCardList,-1,this@MainActivity)

            for((view,adapter) in views.zip(adapters)){
                view.adapter = adapter
                view.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            }
            binding.bottomCardListView.adapter = bottomAdapter
            binding.bottomCardListView.layoutManager =
                GridLayoutManager(this, 2, GridLayoutManager.HORIZONTAL, false)
        }

        // 5명 버튼 클릭
        binding.people5Btn.setOnClickListener {
            binding.cardD.visibility = View.VISIBLE
            binding.cardE.visibility = View.VISIBLE

            luckyGame.setParticipantsNumbers(5)

            val adapters : MutableList<CardListAdapter> = mutableListOf()
            for (i in 0 until luckyGame.participantsCnt){
                adapters.add(CardListAdapter(luckyGame.participantsList[i].ownCardList,i, this@MainActivity))
            }
            val bottomAdapter = CardListAdapter(luckyGame.bottomCardList,-1,this@MainActivity)

            for((view,adapter) in views.zip(adapters)){
                view.adapter = adapter
                view.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            }
            binding.bottomCardListView.adapter = bottomAdapter
            binding.bottomCardListView.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        }

    }

    override fun onFlipCard(card: Card, position: Int, userId: Int): Boolean {

        if(turn[userId] == 0) return false
        if(luckyGame.flipCard(userId,position)){
            turn[userId] = turn[userId]!! - 1
            return true
        }
        return false
    }
}