package com.vitoria.desafioitau.data.repository

import com.vitoria.desafioitau.data.TransactionsResult

interface TransactionsRepository {

    fun getTransactions(resultCallback: (result: TransactionsResult) -> Unit)
}