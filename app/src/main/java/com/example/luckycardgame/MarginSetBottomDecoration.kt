package com.example.luckycardgame

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MarginSetBottomDecoration() : RecyclerView.ItemDecoration() {

    enum class MarginValue(val num: Int) {
        MIN(2),
        MID(18),
        MAX(50),
        TOP(35)
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {

        val position = parent.getChildAdapterPosition(view)
        // 9, 8, 6
        when (parent.adapter?.itemCount) {
            9 -> {
                outRect.right = MarginValue.MID.num
                if (position % 2 == 1) {
                    outRect.top = MarginValue.TOP.num
                }
            }

            8 -> {
                outRect.right = MarginValue.MAX.num
                if (position % 2 == 1) {
                    outRect.top = MarginValue.TOP.num
                }

            }

            6 -> {
                outRect.right = MarginValue.MIN.num
            }
        }

    }
}