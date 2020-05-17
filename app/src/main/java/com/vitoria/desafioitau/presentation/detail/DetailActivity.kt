package com.vitoria.desafioitau.presentation.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.vitoria.desafioitau.R
import com.vitoria.desafioitau.presentation.base.BaseActivity
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.toolbar.*

class DetailActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        setupToolbar(toolbar, R.string.app_name)
        tv_activity_detail_amount_valuer.text = intent.getStringExtra(EXTRA_AMOUNT)
        tv_activity_detail_source_value.text = intent.getStringExtra(EXTRA_SOURCE)
        tv_activity_detail_category_value.text = intent.getStringExtra(EXTRA_CATEGORY)
    }

    companion object {
        private const val EXTRA_SOURCE = "EXTRA_SOURCE"
        private const val EXTRA_AMOUNT = "EXTRA_AMOUNT"
        private const val EXTRA_CATEGORY = "EXTRA_CATEGORY"


        fun getStartIntent(
            context: Context,
            amount: String,
            source: String,
            category: String
        ): Intent {
            return Intent(context, DetailActivity::class.java).apply {
                putExtra(EXTRA_SOURCE, source)
                putExtra(EXTRA_AMOUNT, amount)
                putExtra(EXTRA_CATEGORY, category)
            }
        }
    }
}