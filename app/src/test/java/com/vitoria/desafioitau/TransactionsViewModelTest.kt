package com.vitoria.desafioitau

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.vitoria.desafioitau.data.MonthsEnum
import com.vitoria.desafioitau.data.Results
import com.vitoria.desafioitau.data.model.Transaction
import com.vitoria.desafioitau.data.repository.TransactionsRepository
import com.vitoria.desafioitau.presentation.transactions.TransactionsViewModel
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TransactionsViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var transactionsLiveDataObserver: Observer<List<Transaction>>

    @Mock
    private lateinit var viewFlipperLiveDataObserver: Observer<Pair<Int, Int?>>

    private lateinit var viewModel: TransactionsViewModel

    @Test
    fun setTransactionsLiveDataSucess() {
        // Arrange
        val transactions = listOf(
            Transaction(1, 50.5, "Uber", 1, 1)
        )

        val resultSuccess = MockRepository(Results.SuccessTransaction(transactions))
        viewModel = TransactionsViewModel(resultSuccess)
        viewModel.transactionsLiveData.observeForever(transactionsLiveDataObserver)
        viewModel.viewFlipperLiveData.observeForever(viewFlipperLiveDataObserver)

        // Act
        viewModel.getTransactions(MonthsEnum.JANEIRO.ordinal)


        // Assert
        verify(transactionsLiveDataObserver).onChanged(transactions)
        verify(viewFlipperLiveDataObserver).onChanged(Pair(1, null))
    }

    @Test
    fun setTransactionsLiveDataError() {
        // Arrange
        val resultServerError = MockRepository(Results.ServerError)
        viewModel = TransactionsViewModel(resultServerError)
        viewModel.viewFlipperLiveData.observeForever(viewFlipperLiveDataObserver)

        // Act
        viewModel.getTransactions(MonthsEnum.JANEIRO.ordinal)

        // Assert
        verify(viewFlipperLiveDataObserver).onChanged(Pair(2, R.string.error_500_generic))
    }
}

class MockRepository(private val result: Results) : TransactionsRepository {
    override fun getTransactions(resultCallback: (result: Results) -> Unit) {
        resultCallback(result)
    }
}
