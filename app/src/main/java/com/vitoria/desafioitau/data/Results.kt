package com.vitoria.desafioitau.data

import com.vitoria.desafioitau.data.model.Category
import com.vitoria.desafioitau.data.model.Transaction

sealed class Results {
    class SuccessCategory(val transactions: List<Category>) : Results()
    class ApiError(val statusCode: Int) : Results()
    object ServerError : Results()
    class SuccessTransaction(val transactions: List<Transaction>) : Results()
}