package com.example.luckycardgame

import android.graphics.Rect
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MarginSetDecoration() : RecyclerView.ItemDecoration() {

    enum class MarginValue(val num: Int) {
        MIN(-50),
        MID(-30),
        MAX(2)
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)


        // 8, 7, 6
        val cnt = parent.adapter?.itemCount
        Log.d("check", "cnt is $cnt , position is $position")
        when (cnt) {
            6 -> {
                if (position != 0) {  // 첫 번째 아이템이 아닌 경우에만 left에 -margin 값 적용
                    outRect.left = MarginValue.MAX.num
                }
            }

            7 -> {
                if (position != 0) {
                    outRect.left = MarginValue.MID.num
                }
            }

            8 -> {
                if (position != 0) {
                    outRect.left = MarginValue.MIN.num
                }
            }
        }
    }
}