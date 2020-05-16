package com.vitoria.desafioitau.presentation

import android.os.Bundle
import com.vitoria.desafioitau.R
import kotlinx.android.synthetic.main.toolbar.*

class TransactionsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transactions)

        setupToolbar(toolbar, R.string.app_name)
    }
}