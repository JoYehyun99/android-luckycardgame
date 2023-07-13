package com.example.luckycardgame

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.luckycardgame.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    companion object {
        const val WINNER = "winner"
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val spaceSet = MarginSetDecoration()
        val winnerList = intent?.getSerializableExtra(WINNER, HashMap<Int, Int>()::class.java)
        val cardViews = listOf(
            binding.cvFirstUser,
            binding.cvSecondUser,
            binding.cvThirdUser,
            binding.cvFourthUser,
            binding.cvFifthUser
        )
        val cardListViews = listOf(
            binding.rvFirst,
            binding.rvSecond,
            binding.rvThird,
            binding.rvFourth,
            binding.rvFifth
        )
        for (view in cardListViews) {
            view.addItemDecoration((spaceSet))
        }

        if (winnerList != null) {
            if (winnerList.isEmpty()) {
                binding.tvResultContent.text = "이번 게임에는 승자가 없습니다"
            } else {
                val winnerUserList = winnerList.values.toList()
                val winnerText = getWinnerText(winnerUserList)
                binding.tvResultContent.text = "이번 게임은 ${winnerText}가 승리했습니다"

                for (winner in winnerUserList) {
                    cardViews[winner].setCardBackgroundColor(ContextCompat.getColor(this, R.color.turn_color))
                    val cards = mutableListOf<Card>()
                    winnerList.filterValues { it == winner }.keys.forEach{it ->
                        cards.addAll(getCardList(it))
                    }
                    cardListViews[winner].adapter = CardListAdapter(cards,winner,null)
                    cardListViews[winner].layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
                }
            }
        }


        binding.btnRestart.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }

    }
    private fun getCardList(cardNum: Int): List<Card>{
        val result : MutableList<Card> = mutableListOf()
        result.add(Card.Dog(cardNum,flipped = false))
        result.add(Card.Cat(cardNum,flipped = false))
        result.add(Card.Cow(cardNum,flipped = false))
        return result
    }
    private fun getWinnerText(winnerUserList: List<Int>): String{
        val result = mutableListOf<String>()
        for(user in winnerUserList){
            when(user){
                0 -> result.add("A")
                1 -> result.add("B")
                2 -> result.add("C")
                3 -> result.add("D")
                4 -> result.add("E")
            }
        }
        return result.toString().replace("[","").replace("]","").replace(",","와 ")
    }

}