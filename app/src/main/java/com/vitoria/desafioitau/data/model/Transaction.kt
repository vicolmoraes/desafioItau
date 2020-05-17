package com.vitoria.desafioitau.data.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class Transaction(
    @JsonProperty("id")
    val id: Int,
    @JsonProperty("valor")
    val amount: Double,
    @JsonProperty("origem")
    val source: String,
    @JsonProperty("categoria")
    val category: Int,
    @JsonProperty("mes_lancamento")
    val month: Int
)