package com.formationandroid.applicationmemoskotlin.swipe

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.formationandroid.applicationmemoskotlin.adapter.MemoAdapter

class Swiper(private var adapter: MemoAdapter?) : ItemTouchHelper.Callback() {

    override fun isLongPressDragEnabled(): Boolean {
        return true
    }

    override fun isItemViewSwipeEnabled(): Boolean {
        return true
    }

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val dragFlagsUpDown = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        val dragFlagsRight = ItemTouchHelper.RIGHT
        return makeMovementFlags(dragFlagsUpDown, dragFlagsRight)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        adapter?.onItemMove(viewHolder.adapterPosition, target.adapterPosition);
        return true;
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        if(direction == ItemTouchHelper.RIGHT) {
            adapter?.onItemDismiss(viewHolder.adapterPosition);
        }
    }
}