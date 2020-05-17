package com.vitoria.desafioitau.data

import com.vitoria.desafioitau.data.model.Transaction

sealed class TransactionsResult {
    class Success(val transactions: List<Transaction>) : TransactionsResult()
    class ApiError(val statusCode: Int) : TransactionsResult()
    object ServerError : TransactionsResult()
}