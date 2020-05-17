package com.vitoria.desafioitau.presentation.detail

import android.os.Bundle
import com.vitoria.desafioitau.R
import com.vitoria.desafioitau.presentation.base.BaseActivity
import kotlinx.android.synthetic.main.toolbar.*

class DetailActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        setupToolbar(toolbar, R.string.app_name)
    }
}