package com.example.luckycardgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.luckycardgame.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), CardListAdapter.OnCardClickListener {

    private val luckyGame = LuckyGame()
    private val flipCnt = mutableMapOf(0 to 3, 1 to 3, 2 to 3, 3 to 3, 4 to 3)
    private lateinit var cardViews: List<CardView>
    private lateinit var bottomAdapter: CardListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val spaceSet = MarginSetDecoration()
        val bottomSpaceSet = MarginSetBottomDecoration()
        cardViews =
            listOf(
                binding.cvFirstUser,
                binding.cvSecondUser,
                binding.cvThirdUser,
                binding.cvFourthUser,
                binding.cvFifthUser
            )
        val views = listOf(
            binding.rvFirst,
            binding.rvSecond,
            binding.rvThird,
            binding.rvFourth,
            binding.rvFifth
        )
        for (view in views) {
            view.addItemDecoration((spaceSet))
        }
        binding.rvBottom.addItemDecoration(bottomSpaceSet)

        binding.btnPeopleCnt.addOnButtonCheckedListener { toggleButton, checkedId, isChecked ->
            if (isChecked) {
                when (checkedId) {
                    R.id.btn_people3 -> {
                        binding.btnPeople3.icon =
                            AppCompatResources.getDrawable(applicationContext, R.drawable.checkicon)
                        binding.btnPeople4.icon = null
                        binding.btnPeople5.icon = null
                    }

                    R.id.btn_people4 -> {
                        binding.btnPeople3.icon = null
                        binding.btnPeople4.icon =
                            AppCompatResources.getDrawable(applicationContext, R.drawable.checkicon)
                        binding.btnPeople5.icon = null
                    }

                    R.id.btn_people5 -> {
                        binding.btnPeople3.icon = null
                        binding.btnPeople4.icon = null
                        binding.btnPeople5.icon =
                            AppCompatResources.getDrawable(applicationContext, R.drawable.checkicon)
                    }
                }
            }
        }

        binding.btnPeople3.setOnClickListener {
            binding.cvFourthUser.visibility = View.INVISIBLE
            binding.cvFifthUser.visibility = View.GONE

            luckyGame.setParticipantsNumbers(3)
            setAdapter(views, binding)
        }
        binding.btnPeople4.setOnClickListener {
            binding.cvFourthUser.visibility = View.VISIBLE
            binding.cvFifthUser.visibility = View.GONE

            luckyGame.setParticipantsNumbers(4)
            setAdapter(views, binding)
        }
        binding.btnPeople5.setOnClickListener {
            binding.cvFourthUser.visibility = View.VISIBLE
            binding.cvFifthUser.visibility = View.VISIBLE

            luckyGame.setParticipantsNumbers(5)
            setAdapter(views, binding)
        }
    }

    private fun setAdapter(views: List<RecyclerView>, binding: ActivityMainBinding) {
        val adapters: MutableList<CardListAdapter> = mutableListOf()
        for (i in 0 until luckyGame.participantsCnt) {
            adapters.add(
                CardListAdapter(
                    luckyGame.participantsList[i].ownCardList,
                    i,
                    this@MainActivity
                )
            )
        }
        for ((view, adapter) in views.zip(adapters)) {
            view.adapter = adapter
            view.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        }
        bottomAdapter = CardListAdapter(luckyGame.bottomCardList, -1, this@MainActivity)
        binding.rvBottom.adapter = bottomAdapter
        if (luckyGame.participantsCnt == 5) {
            binding.rvBottom.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        } else {
            binding.rvBottom.layoutManager =
                GridLayoutManager(this, 2, GridLayoutManager.HORIZONTAL, false)
        }
        luckyGame.nowturn = 0
        cardViews[luckyGame.nowturn].setCardBackgroundColor(
            ContextCompat.getColor(
                this,
                R.color.turn_color
            )
        )
    }

    private fun switchTurn(userId: Int) {
        if (luckyGame.nowturn == (luckyGame.participantsCnt - 1)) { // 마지막 턴이였을 경우, 게임 종료 조건 확인
            Handler(Looper.getMainLooper()).postDelayed({
                if (luckyGame.isGameEnd()) {
                    val intent = Intent(this, ResultActivity::class.java)
                    intent.putExtra(ResultActivity.WINNER, luckyGame.winnerList)
                    startActivity(intent)
                } else {
                    if (luckyGame.flippedBottomCardList.isNotEmpty()) {
                        luckyGame.flippedBottomCardList.clear()
                        bottomAdapter.notifyDataSetChanged()
                    }
                    setTurnBackground()
                    flipCnt[userId] = 3
                }
            }, 700)
        } else {
            setTurnBackground()
            flipCnt[userId] = 3
        }
    }

    private fun setTurnBackground() {
        cardViews[luckyGame.switchTurn()].setCardBackgroundColor(
            ContextCompat.getColor(
                this,
                R.color.base_color
            )
        )
        cardViews[luckyGame.nowturn].setCardBackgroundColor(
            ContextCompat.getColor(
                this,
                R.color.turn_color
            )
        )
    }

    override fun onFlipCard(card: Card, position: Int, userId: Int): Boolean {
        if (userId != -1 && luckyGame.nowturn != userId) return false
        if (flipCnt[luckyGame.nowturn] == 1 && luckyGame.flipCard(
                userId,
                position
            )
        ) { // 한 turn의 마지막 유저가 뒤집었을 때
            switchTurn(luckyGame.nowturn)
            return true
        }
        if (luckyGame.flipCard(userId, position)) {
            flipCnt[luckyGame.nowturn] = flipCnt[luckyGame.nowturn]!! - 1
            return true
        }
        return false
    }

}