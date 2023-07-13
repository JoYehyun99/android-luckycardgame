package com.example.luckycardgame

import android.animation.ObjectAnimator
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.luckycardgame.databinding.CardItemBinding

class CardListAdapter(
    private val cardList: MutableList<Card>,
    private val userId: Int,
    private val listener: OnCardClickListener
) :
    RecyclerView.Adapter<CardListAdapter.CardListViewHolder>() {
    interface OnCardClickListener {
        fun onFlipCard(card: Card, position: Int, userId: Int): Boolean
    }

    inner class CardListViewHolder(binding: CardItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val topNum = binding.topNum
        val bottomNum = binding.bottomNum
        val animalImg = binding.animalIcon
        val backsideImg = binding.backsideImg
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardListViewHolder {
        val binding = CardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CardListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return cardList.count()
    }

    override fun onBindViewHolder(holder: CardListViewHolder, position: Int) {

        val cardItem = cardList[position]

        if (cardItem.flipped) {
            holder.topNum.visibility = View.INVISIBLE
            holder.bottomNum.visibility = View.INVISIBLE
            holder.animalImg.visibility = View.INVISIBLE
            holder.backsideImg.visibility = View.VISIBLE
        } else {
            setFrontView(cardItem, holder)
        }

        holder.itemView.setOnClickListener {
            if (cardItem.flipped) {
                if (listener.onFlipCard(cardList[position], position, userId)) {
                    setFrontView(cardItem, holder)
                }
            }
        }
    }

    private fun setFrontView(cardItem: Card, holder: CardListViewHolder) {

        val rotate = ObjectAnimator.ofFloat(holder.itemView,"rotationY",180f,0f)
        rotate.duration = 300
        rotate.start()

        Handler(Looper.getMainLooper()).postDelayed({
            holder.backsideImg.visibility = View.INVISIBLE
            holder.topNum.visibility = View.VISIBLE
            holder.bottomNum.visibility = View.VISIBLE
            holder.animalImg.visibility = View.VISIBLE

            when (cardItem) {
                is Card.Dog -> {
                    holder.topNum.text = cardItem.cardNum.toString()
                    holder.bottomNum.text = cardItem.cardNum.toString()
                    holder.animalImg.text = cardItem.unicode
                }

                is Card.Cat -> {
                    holder.topNum.text = cardItem.cardNum.toString()
                    holder.bottomNum.text = cardItem.cardNum.toString()
                    holder.animalImg.text = cardItem.unicode
                }

                is Card.Cow -> {
                    holder.topNum.text = cardItem.cardNum.toString()
                    holder.bottomNum.text = cardItem.cardNum.toString()
                    holder.animalImg.text = cardItem.unicode
                }
            }
        },200)
    }
}