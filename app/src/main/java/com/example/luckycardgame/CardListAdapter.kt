package com.example.luckycardgame

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CardListAdapter(private val cardList: MutableList<Card>) :
    RecyclerView.Adapter<CardListAdapter.CardListViewHolder>() {

    inner class CardListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val topNum = itemView.findViewById<TextView>(R.id.top_num)
        val bottomNum = itemView.findViewById<TextView>(R.id.bottom_num)
        val animalImg = itemView.findViewById<TextView>(R.id.animal_icon)
        val backsideImg = itemView.findViewById<ImageView>(R.id.backside_img)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_item, parent, false)
        return CardListViewHolder(view)
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
                    holder.animalImg.text = String(Character.toChars(cardItem.unicode))
                }

                is Card.Cat -> {
                    holder.topNum.text = (cardItem as Card.Cat).cardNum.toString()
                    holder.bottomNum.text = cardItem.cardNum.toString()
                    holder.animalImg.text = String(Character.toChars(cardItem.unicode))
                }

                is Card.Cow -> {
                    holder.topNum.text = (cardItem as Card.Cow).cardNum.toString()
                    holder.bottomNum.text = cardItem.cardNum.toString()
                    holder.animalImg.text = String(Character.toChars(cardItem.unicode))
                }

            }
        }

    }
}