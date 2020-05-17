package com.vitoria.desafioitau.data.repository

import com.vitoria.desafioitau.data.Results
import com.vitoria.desafioitau.data.RetrofitConfig
import com.vitoria.desafioitau.data.model.Category
import com.vitoria.desafioitau.data.model.Transaction
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiDataSource : TransactionsRepository, CategoriesRepository {

    override fun getTransactions(resultCallback: (result: Results) -> Unit) {

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
                                    val aux = result
                                    transactions.add(aux)
                                }
                            }
                            resultCallback(Results.SuccessTransaction(transactions))

                        }
                        else -> resultCallback(Results.ApiError(response.code()))
                    }
                }

                override fun onFailure(call: Call<List<Transaction>>, t: Throwable) {
                    resultCallback(Results.ServerError)
                }
            })
    }

    override fun getCategories(resultCallback: (result: Results) -> Unit) {

        RetrofitConfig().buildingService().getCategories()
            .enqueue(object : Callback<List<Category>> {
                override fun onResponse(
                    call: Call<List<Category>>,
                    response: Response<List<Category>>
                ) {
                    when {
                        response.isSuccessful -> {
                            val categories: MutableList<Category> = mutableListOf()

                            response.body()?.let { responses ->
                                for (result in responses) {
                                    val aux = result
                                    categories.add(aux)
                                }
                            }
                            resultCallback(Results.SuccessCategory(categories))

                        }
                        else -> resultCallback(Results.ApiError(response.code()))
                    }
                }

                override fun onFailure(call: Call<List<Category>>, t: Throwable) {
                    resultCallback(Results.ServerError)
                }
            })
    }
}