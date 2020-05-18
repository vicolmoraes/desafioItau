package com.vitoria.desafioitau.presentation.transactions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vitoria.desafioitau.R
import com.vitoria.desafioitau.data.MonthsEnum
import com.vitoria.desafioitau.data.model.Transaction
import kotlinx.android.synthetic.main.item_transaction.view.*
import java.text.NumberFormat

class TransactionsAdapter(
    private val transactions: List<Transaction>,
    private val onItemClickListener: ((transaction: Transaction) -> Unit)
) : RecyclerView.Adapter<TransactionsAdapter.TransactionsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, view: Int): TransactionsViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_transaction, parent, false)
        return TransactionsViewHolder(itemView, onItemClickListener)
    }

    override fun getItemCount() = transactions.count()

    override fun onBindViewHolder(viewHolder: TransactionsViewHolder, position: Int) {
        viewHolder.bindView(transactions[position])
    }

    class TransactionsViewHolder(
        itemView: View,
        private val onItemClickListener: ((transaction: Transaction) -> Unit)
    ) : RecyclerView.ViewHolder(itemView) {

        private val source = itemView.tv_item_transaction_source_value
        private val amount = itemView.tv_item_transaction_amount_valuer
        private val month = itemView.tv_item_transaction_month
        private val icon = itemView.iv_item_transaction_category_icon

        fun bindView(transaction: Transaction) {
            source.text = transaction.source
            amount.text = NumberFormat.getCurrencyInstance().format(transaction.amount)
            month.text = MonthsEnum.values().get(transaction.month).month

            when (transaction.category) {
                1 -> icon.setBackgroundResource(R.drawable.ic_car)
                2 -> icon.setBackgroundResource(R.drawable.ic_money)
                3 -> icon.setBackgroundResource(R.drawable.ic_beauty)
                4 -> icon.setBackgroundResource(R.drawable.ic_mechanical)
                5 -> icon.setBackgroundResource(R.drawable.ic_restaurant)
                6 -> icon.setBackgroundResource(R.drawable.ic_supermarket)
            }

            itemView.setOnClickListener {
                onItemClickListener.invoke(transaction)
            }
        }
    }
}