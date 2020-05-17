package com.vitoria.desafioitau.data.repository

import com.vitoria.desafioitau.data.RetrofitConfig
import com.vitoria.desafioitau.data.TransactionsResult
import com.vitoria.desafioitau.data.model.Transaction
import com.vitoria.desafioitau.data.model.Transactions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TransactionsApiDataSource : TransactionsRepository {

    override fun getTransactions(resultCallback: (result: TransactionsResult) -> Unit) {

        RetrofitConfig().buildingService().getTransactions()
            .enqueue(object : Callback<Transactions> {
                override fun onResponse(
                    call: Call<Transactions>,
                    response: Response<Transactions>
                ) {
                    when {
                        response.isSuccessful -> {
                            val transactions: List<Transaction>

                            response.body()?.let { responses ->
                                transactions = responses.transactions
                                resultCallback(TransactionsResult.Success(transactions))
                            }

                        }
                        else -> resultCallback(TransactionsResult.ApiError(response.code()))
                    }
                }

                override fun onFailure(call: Call<Transactions>, t: Throwable) {
                    resultCallback(TransactionsResult.ServerError)
                }
            })
    }
}