package com.vitoria.desafioitau.presentation.transactions

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import com.vitoria.desafioitau.R
import com.vitoria.desafioitau.data.MonthsEnum
import com.vitoria.desafioitau.data.repository.ApiDataSource
import com.vitoria.desafioitau.presentation.base.BaseActivity
import com.vitoria.desafioitau.presentation.detail.DetailActivity
import kotlinx.android.synthetic.main.activity_transactions.*
import java.text.NumberFormat

class TransactionsActivity : BaseActivity() {
    var monthNumber: Int = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transactions)

        setupToolbar(in_toolbar as Toolbar, R.string.transactions_activity_title, null)

        setData()
        setMonths()
    }

    private fun setMonths() {
        with(rv_activity_transactions_months) {
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(
                this@TransactionsActivity,
                androidx.recyclerview.widget.RecyclerView.HORIZONTAL,
                false
            )
            setHasFixedSize(true)
            var monthsList: ArrayList<MonthsEnum> = ArrayList()
            monthsList.addAll(MonthsEnum.values())
            val list = monthsList.subList(1, monthsList.size)
            adapter = MonthsAdapter(list) { number ->
                monthNumber = number
                setData()
            }
        }
    }

    private fun setData() {
        val viewModel: TransactionsViewModel = TransactionsViewModel.ViewModelFactory(
            ApiDataSource()
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
                            transaction.amount,
                            transaction.source,
                            transaction.category
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

        viewModel.transactionsSum.observe(this, Observer {
            it?.let { sum ->
                setupToolbar(
                    in_toolbar as Toolbar,
                    0,
                    MonthsEnum.values().get(monthNumber).month + " " + NumberFormat.getCurrencyInstance().format(
                        sum
                    )
                )
            }
        })

        viewModel.getTransactions(monthNumber)

    }
}