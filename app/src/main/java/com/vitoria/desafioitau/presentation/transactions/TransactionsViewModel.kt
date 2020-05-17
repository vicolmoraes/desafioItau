package com.vitoria.desafioitau.presentation.transactions

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vitoria.desafioitau.R
import com.vitoria.desafioitau.data.TransactionsResult
import com.vitoria.desafioitau.data.model.Transaction
import com.vitoria.desafioitau.data.repository.TransactionsRepository

class TransactionsViewModel(private val dataSource: TransactionsRepository) : ViewModel() {

    val transactionsLiveData: MutableLiveData<List<Transaction>> = MutableLiveData()
    val viewFlipperLiveData: MutableLiveData<Pair<Int, Int?>> = MutableLiveData()

    fun getTransactions(month: Int) {
        dataSource.getTransactions { result: TransactionsResult ->
            when (result) {
                is TransactionsResult.Success -> {
                    var monthList: ArrayList<Transaction> = ArrayList()
                    for (transaction in result.transactions) {
                        if (transaction.month == month) {
                            monthList.add(transaction)
                        }
                    }
                    transactionsLiveData.value = monthList
                    viewFlipperLiveData.value = Pair(VIEW_FLIPPER_TRANSACTIONS, null)
                }
                is TransactionsResult.ApiError -> {
                    if (result.statusCode == 401) {
                        viewFlipperLiveData.value =
                            Pair(VIEW_FLIPPER_ERROR, R.string.error_401)
                    } else {
                        viewFlipperLiveData.value =
                            Pair(VIEW_FLIPPER_ERROR, R.string.error_400_generic)
                    }
                }
                is TransactionsResult.ServerError -> {
                    viewFlipperLiveData.value =
                        Pair(VIEW_FLIPPER_ERROR, R.string.error_500_generic)
                }
            }
        }
    }

    class ViewModelFactory(private val dataSource: TransactionsRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(TransactionsViewModel::class.java)) {
                return TransactionsViewModel(dataSource) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

    companion object {
        private const val VIEW_FLIPPER_TRANSACTIONS = 1
        private const val VIEW_FLIPPER_ERROR = 2
    }
}