package com.vitoria.desafioitau.data.repository

import com.vitoria.desafioitau.data.Results

interface TransactionsRepository {

    fun getTransactions(resultCallback: (result: Results) -> Unit)
}