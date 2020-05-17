package com.vitoria.desafioitau.data.model

data class Transaction(
    val id: Int,
    val amount: Double,
    val source: String,
    val month: Int
)