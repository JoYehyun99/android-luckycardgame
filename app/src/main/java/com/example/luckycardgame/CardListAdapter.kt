package com.example.luckycardgame

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.luckycardgame.databinding.CardItemBinding

class CardListAdapter(private val cardList: MutableList<Card>) :
    RecyclerView.Adapter<CardListAdapter.CardListViewHolder>() {

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

        if (cardList[position].flipped) {
            holder.topNum.visibility = View.INVISIBLE
            holder.bottomNum.visibility = View.INVISIBLE
            holder.animalImg.visibility = View.INVISIBLE
            holder.backsideImg.visibility = View.VISIBLE
        } else {
            holder.backsideImg.visibility = View.INVISIBLE
            val cardItem = cardList[position]
            when (cardList[position]) {
                is Card.Dog -> {
                    holder.topNum.text = (cardItem as Card.Dog).cardNum.toString()
                    holder.bottomNum.text = cardItem.cardNum.toString()
                    holder.animalImg.text = cardItem.unicode
                }
                is Card.Cat -> {
                    holder.topNum.text = (cardItem as Card.Cat).cardNum.toString()
                    holder.bottomNum.text = cardItem.cardNum.toString()
                    holder.animalImg.text = cardItem.unicode
                }
                is Card.Cow -> {
                    holder.topNum.text = (cardItem as Card.Cow).cardNum.toString()
                    holder.bottomNum.text = cardItem.cardNum.toString()
                    holder.animalImg.text = cardItem.unicode
                }
            }
        }
    }
}