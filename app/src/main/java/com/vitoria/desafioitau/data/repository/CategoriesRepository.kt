package com.vitoria.desafioitau.data.repository

import com.vitoria.desafioitau.data.Results

interface CategoriesRepository {

    fun getCategories(resultCallback: (result: Results) -> Unit)
}