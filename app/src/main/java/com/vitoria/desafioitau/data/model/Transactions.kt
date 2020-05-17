package com.vitoria.desafioitau.data.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class Transactions(
    val transactions: List<Transaction>
)