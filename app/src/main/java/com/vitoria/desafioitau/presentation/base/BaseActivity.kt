package com.vitoria.desafioitau.presentation.base

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {

    protected fun setupToolbar(
        toolbar: Toolbar,
        titleIdRes: Int,
        title: String?,
        showBackButton: Boolean = false
    ) {
        if (titleIdRes != 0)
            toolbar.title = getString(titleIdRes)
        else
            toolbar.title = title
        setSupportActionBar(toolbar)
        if (showBackButton) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }
}