package com.vitoria.desafioitau.presentation.transactions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vitoria.desafioitau.R
import com.vitoria.desafioitau.data.model.Transaction
import kotlinx.android.synthetic.main.item_transaction.view.*

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

        fun bindView(transaction: Transaction) {
            source.text = transaction.source
            amount.text = transaction.amount.toString()

            itemView.setOnClickListener {
                onItemClickListener.invoke(transaction)
            }
        }
    }
}