package com.vitoria.desafioitau.presentation.transactions

import android.os.Bundle
import androidx.lifecycle.Observer
import com.vitoria.desafioitau.R
import com.vitoria.desafioitau.data.repository.TransactionsApiDataSource
import com.vitoria.desafioitau.presentation.base.BaseActivity
import com.vitoria.desafioitau.presentation.detail.DetailActivity
import kotlinx.android.synthetic.main.activity_transactions.*
import kotlinx.android.synthetic.main.toolbar.*

class TransactionsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transactions)

        setupToolbar(toolbar, R.string.app_name)

        val viewModel: TransactionsViewModel = TransactionsViewModel.ViewModelFactory(
            TransactionsApiDataSource()
        )
            .create(TransactionsViewModel::class.java)

        viewModel.transactionsLiveData.observe(this, Observer {
            it?.let { transactions ->
                with(rv_activity_transactions_list) {
                    layoutManager = androidx.recyclerview.widget.LinearLayoutManager(
                        this@TransactionsActivity,
                        androidx.recyclerview.widget.RecyclerView.VERTICAL,
                        false
                    )
                    setHasFixedSize(true)
                    adapter = TransactionsAdapter(transactions) { transaction ->
                        val intent = DetailActivity.getStartIntent(
                            this@TransactionsActivity,
                            transaction.amount.toString(),
                            transaction.source,
                            transaction.category.toString()
                        )
                        this@TransactionsActivity.startActivity(intent)
                    }
                }
            }
        })

        viewModel.viewFlipperLiveData.observe(this, Observer {
            it?.let { viewFlipper ->
                vf_activity_transactions.displayedChild = viewFlipper.first

                viewFlipper.second?.let { errorMessageResId ->
                    tv_activity_transactions_error.text = getString(errorMessageResId)
                }
            }
        })

        viewModel.getTransactions()
    }
}