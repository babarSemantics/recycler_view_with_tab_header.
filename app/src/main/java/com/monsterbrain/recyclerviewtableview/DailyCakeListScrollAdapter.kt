package com.monsterbrain.recyclerviewtableview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_menu_list.view.*


class DailyCakeListScrollAdapter(
    var categoryMenuItemList1: List<String>
) : RecyclerView.Adapter<DailyCakeListScrollAdapter.FirstViewHolderr>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): FirstViewHolderr {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.layout_menu_list, viewGroup, false)
        return FirstViewHolderr(v)
    }

    override fun onBindViewHolder(firstViewHolder: FirstViewHolderr, i: Int) {
        firstViewHolder.itemView.text_cake.text = categoryMenuItemList1[i]
        firstViewHolder.itemView.textCount.text = "Pizza " + i + 1
    }

    override fun getItemCount(): Int {
        return categoryMenuItemList1.size
    }

    inner class FirstViewHolderr(itemView: View) : RecyclerView.ViewHolder(itemView)
}