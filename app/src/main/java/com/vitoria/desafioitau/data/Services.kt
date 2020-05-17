package com.vitoria.desafioitau.data

import com.vitoria.desafioitau.data.model.Transaction
import retrofit2.Call
import retrofit2.http.GET

interface Services {

    @GET("lancamentos/")
    fun getTransactions(): Call<List<Transaction>>

    @GET("categorias/")
    fun getCategories(): Call<List<Transaction>>
}