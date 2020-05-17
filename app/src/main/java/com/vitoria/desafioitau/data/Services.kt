package com.vitoria.desafioitau.data

import com.vitoria.desafioitau.data.model.Transactions
import retrofit2.Call
import retrofit2.http.GET

interface Services {

    @GET("lancamentos")
    fun getTransactions(): Call<Transactions>

    @GET("categorias")
    fun getCategories(): Call<Transactions>
}