package com.example.luckycardgame

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MarginSetDecoration(private val spaceSize : Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        if(position != 0){  // 첫 번째 아이템이 아닌 경우에만 left에 -margin 값 적용
            outRect.left = spaceSize
        }

        //val a = parent.adapter?.itemCount
    }
}