package com.vitoria.desafioitau.data.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class Category(
    @JsonProperty("id")
    val id: Int,
    @JsonProperty("nome")
    val name: String

)