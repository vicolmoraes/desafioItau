package com.vitoria.desafioitau.presentation.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import com.vitoria.desafioitau.R
import com.vitoria.desafioitau.data.repository.ApiDataSource
import com.vitoria.desafioitau.presentation.base.BaseActivity
import kotlinx.android.synthetic.main.activity_detail.*
import java.text.NumberFormat

class DetailActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        setupToolbar(in_toolbar as Toolbar, R.string.detail_activity_title, null)

        tv_activity_detail_amount_valuer.text =
            NumberFormat.getCurrencyInstance().format(intent.getDoubleExtra(EXTRA_AMOUNT, 0.00))
        tv_activity_detail_source_value.text = intent.getStringExtra(EXTRA_SOURCE)

        setData()
    }

    private fun setData() {
        val viewModel: DetailViewModel = DetailViewModel.ViewModelFactory(
            ApiDataSource()
        )
            .create(DetailViewModel::class.java)

        viewModel.categoriesLiveData.observe(this, Observer {
            it?.let { transactions ->
                tv_activity_detail_category_value.text = transactions
            }
        })

        viewModel.viewFlipperLiveData.observe(this, Observer {
            it?.let { viewFlipper ->
                vf_activity_detail.displayedChild = viewFlipper.first

                viewFlipper.second?.let { errorMessageResId ->
                    tv_activity_detail_error.text = getString(errorMessageResId)
                }
            }
        })

        viewModel.getCategories(intent.getIntExtra(EXTRA_CATEGORY, 0))
    }

    companion object {
        private const val EXTRA_SOURCE = "EXTRA_SOURCE"
        private const val EXTRA_AMOUNT = "EXTRA_AMOUNT"
        private const val EXTRA_CATEGORY = "EXTRA_CATEGORY"


        fun getStartIntent(
            context: Context,
            amount: Double,
            source: String,
            category: Int
        ): Intent {
            return Intent(context, DetailActivity::class.java).apply {
                putExtra(EXTRA_SOURCE, source)
                putExtra(EXTRA_AMOUNT, amount)
                putExtra(EXTRA_CATEGORY, category)
            }
        }
    }
}