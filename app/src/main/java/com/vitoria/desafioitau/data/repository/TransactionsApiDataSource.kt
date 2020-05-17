package com.vitoria.desafioitau.data.repository

import com.vitoria.desafioitau.data.RetrofitConfig
import com.vitoria.desafioitau.data.TransactionsResult
import com.vitoria.desafioitau.data.model.Transaction
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TransactionsApiDataSource : TransactionsRepository {

    override fun getTransactions(resultCallback: (result: TransactionsResult) -> Unit) {

        RetrofitConfig().buildingService().getTransactions()
            .enqueue(object : Callback<List<Transaction>> {
                override fun onResponse(
                    call: Call<List<Transaction>>,
                    response: Response<List<Transaction>>
                ) {
                    when {
                        response.isSuccessful -> {
                            val transactions: MutableList<Transaction> = mutableListOf()

                            response.body()?.let { responses ->
                                for (result in responses) {
                                    val book = result
                                    transactions.add(book)
                                }
                            }
                            resultCallback(TransactionsResult.Success(transactions))

                        }
                        else -> resultCallback(TransactionsResult.ApiError(response.code()))
                    }
                }

                override fun onFailure(call: Call<List<Transaction>>, t: Throwable) {
                    resultCallback(TransactionsResult.ServerError)
                }
            })
    }
}