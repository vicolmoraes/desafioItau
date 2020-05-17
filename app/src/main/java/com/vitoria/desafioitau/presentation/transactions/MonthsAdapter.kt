package com.vitoria.desafioitau.presentation.transactions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vitoria.desafioitau.R
import com.vitoria.desafioitau.data.MonthsEnum
import kotlinx.android.synthetic.main.item_month.view.*

class MonthsAdapter(
    private val months: List<MonthsEnum>,
    private val onItemClickListener: ((month: Int) -> Unit)
) : RecyclerView.Adapter<MonthsAdapter.MonthsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, view: Int): MonthsViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_month, parent, false)
        return MonthsViewHolder(itemView, onItemClickListener)
    }

    override fun getItemCount() = months.count()

    override fun onBindViewHolder(viewHolder: MonthsViewHolder, position: Int) {
        viewHolder.bindView(months[position])
    }

    class MonthsViewHolder(
        itemView: View,
        private val onItemClickListener: ((month: Int) -> Unit)
    ) : RecyclerView.ViewHolder(itemView) {

        private val monthButton = itemView.bt_item_month

        fun bindView(monthName: MonthsEnum) {
            monthButton.text = monthName.month

            itemView.setOnClickListener {
                onItemClickListener.invoke(monthName.ordinal)
            }
        }
    }
}