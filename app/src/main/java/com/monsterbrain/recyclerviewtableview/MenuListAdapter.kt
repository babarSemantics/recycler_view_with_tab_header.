package com.monsterbrain.recyclerviewtableview

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_menu.view.*


class MenuListAdapter(
    private val categoryMenuItemList: List<String>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<MenuListAdapter.ViewHolder>() {
    var flag = false
    var positionSelected = 0

    interface OnItemClickListener {
        fun onItemClick(position: Int, check: Boolean)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_menu, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.menu_name.text = categoryMenuItemList[position]
        flag = if (positionSelected == position) {
            viewHolder.itemView.menu_name.setTextColor(Color.parseColor("#D1007E"))
            viewHolder.itemView.text_menu_counts.setTextColor(Color.parseColor("#D1007E"))
            false
        } else {
            viewHolder.itemView.menu_name.setTextColor(Color.parseColor("#000000"))
            viewHolder.itemView.text_menu_counts.setTextColor(Color.parseColor("#000000"))
            true
        }
    }

    override fun getItemCount(): Int {
        return categoryMenuItemList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                positionSelected = adapterPosition
                notifyDataSetChanged()
                listener.onItemClick(positionSelected, true)
            }
        }
    }
}